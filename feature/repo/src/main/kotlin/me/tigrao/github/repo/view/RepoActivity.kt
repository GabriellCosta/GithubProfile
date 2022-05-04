package me.tigrao.github.repo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import br.com.hippopotamus.tabarato.designsystem.viewstate.StateViewActionDispatcher
import br.com.tabarato.infra.action.dispatcher.ViewAction
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.tigrao.github.repo.databinding.ActivityRepoBinding
import me.tigrao.github.repo.presentation.RepoViewModel
import me.tigrao.github.repo.presentation.model.RepoAction
import me.tigrao.github.repo.presentation.model.RepoEvent
import me.tigrao.github.repo.presentation.model.RepoSate
import me.tigrao.github.repo.view.adapter.LayoutManagerFactory
import me.tigrao.github.repo.view.adapter.RepoAdapter
import me.tigrao.github.repo.view.adapter.RepoLoadStateAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepoActivity : AppCompatActivity(), StateViewActionDispatcher {

    private val binder by viewBinding<RepoActivity, ActivityRepoBinding> {
        ActivityRepoBinding.inflate(layoutInflater)
    }

    private val viewModel: RepoViewModel by viewModel()
    private val repoAdapter by inject<RepoAdapter>()
    private val layoutMangerFactory by inject<LayoutManagerFactory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binder.root)

        binder.state.stateViewDispatchAction = this
        prepareList()
        prepareObservers()
        preparePagerCollect()
        prepareStateFlowRead()
    }

    private fun preparePagerCollect() {
        lifecycleScope.launch {
            viewModel.reposPager.collectLatest { pagingData ->
                repoAdapter.submitData(pagingData)
            }
        }
    }

    private fun prepareStateFlowRead() {
        lifecycleScope.launch {
            repoAdapter.loadStateFlow.collect {
                viewModel.dispatch(
                    RepoAction.CollectState(it, repoAdapter.itemCount)
                )
            }
        }
    }

    private fun prepareObservers() {
        viewModel.state.observe(this, ::readState)

        viewModel.event.observe(this, ::readEvent)
    }

    private fun readState(state: RepoSate) = when (state) {
        is RepoSate.EmptyState -> onEmptyState(state)
        RepoSate.SuccessState -> onSuccessState()
    }

    private fun readEvent(event: RepoEvent) = when (event) {
        RepoEvent.TryAgain -> tryAgain()
    }

    private fun tryAgain() {
        repoAdapter.retry()

        //TODO: Should I move this to the VM
        if (binder.state.isVisible) {
            binder.state.isVisible = false
            binder.loadingRepo.isVisible = true
        }
    }

    private fun onSuccessState() {
        binder.loadingRepo.isVisible = false
        binder.state.isVisible = false
        binder.rvRepo.isVisible = true
    }

    private fun onEmptyState(state: RepoSate.EmptyState) {
        binder.state.isVisible = true
        binder.loadingRepo.isVisible = false

        binder.state.prepareLayout(state.state)
    }

    private fun prepareList() {
        val createLayoutManager = layoutMangerFactory.createLayoutManager(this)

        with(binder.rvRepo) {
            val dividerItemDecoration = DividerItemDecoration(
                context,
                createLayoutManager.orientation
            )
            addItemDecoration(dividerItemDecoration)
            adapter = repoAdapter.withLoadStateFooter(RepoLoadStateAdapter(repoAdapter))
            layoutManager = createLayoutManager
        }
    }

    override fun dispatch(action: ViewAction) {
        viewModel.dispatch(action as RepoAction)
    }
}
