package me.tigrao.github.repo.presentation.model

import br.com.tabarato.infra.action.dispatcher.ViewAction

internal sealed interface RepoEvent: ViewAction {
    
    object TryAgain: RepoEvent
}
