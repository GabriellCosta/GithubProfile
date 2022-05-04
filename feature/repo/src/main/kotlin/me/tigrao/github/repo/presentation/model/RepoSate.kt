package me.tigrao.github.repo.presentation.model

import br.com.hippopotamus.tabarato.designsystem.viewstate.StateViewArg

internal sealed interface RepoSate {

    data class EmptyState(val state: StateViewArg) : RepoSate

    object SuccessState : RepoSate
}
