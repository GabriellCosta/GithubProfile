package me.tigrao.github.repo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.cachedIn
import br.com.tabarato.infra.action.dispatcher.ActionDispatcher
import me.tigrao.github.repo.presentation.model.RepoAction
import me.tigrao.github.repo.presentation.model.RepoEvent
import me.tigrao.github.repo.presentation.model.RepoSate

internal class RepoViewModel(
    pagerProvider: PagerProvider,
    private val stateViewFactory: StateViewFactory,
) : ViewModel(), ActionDispatcher<RepoAction> {

    val reposPager = pagerProvider.providePager().cachedIn(viewModelScope)

    private val _state = MutableLiveData<RepoSate>()
    val state: LiveData<RepoSate> = _state

    private val _event = MutableLiveData<RepoEvent>()
    val event: LiveData<RepoEvent> = _event

    override fun dispatch(action: RepoAction) {
        when (action) {
            is RepoAction.CollectState -> onCollectStates(action)
            RepoAction.TryAgain -> onTryAgain()
        }
    }

    private fun onTryAgain() {
        _event.postValue(RepoEvent.TryAgain)
    }

    private fun onCollectStates(action: RepoAction.CollectState) {
        with(action.state) {
            when (this.refresh) {
                is LoadState.Error -> {
                    val state = if (contentIsEmpty(action.itemCount)) {
                        RepoSate.EmptyState(stateViewFactory.genericError())
                    } else {
                        RepoSate.SuccessState
                    }

                    _state.postValue(state)
                }
                is LoadState.NotLoading -> {
                    val state =
                        if (append.endOfPaginationReached && contentIsEmpty(action.itemCount)) {
                            RepoSate.EmptyState(stateViewFactory.emptyState())
                        } else {
                            RepoSate.SuccessState
                        }

                    _state.postValue(state)
                }
                LoadState.Loading -> {}
            }
        }
    }

    private fun contentIsEmpty(itemCount: Int) = itemCount < 1
}
