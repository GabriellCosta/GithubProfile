package me.tigrao.github.repo.presentation.model

import androidx.paging.CombinedLoadStates
import br.com.tabarato.infra.action.dispatcher.ViewAction

internal sealed interface RepoAction : ViewAction {

    data class CollectState(val state: CombinedLoadStates, val itemCount: Int) : RepoAction

    object TryAgain : RepoAction
}
