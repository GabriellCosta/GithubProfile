package me.tigrao.github.repo.presentation

import android.content.res.Resources
import br.com.hippopotamus.tabarato.designsystem.viewstate.ButtonViewArg
import br.com.hippopotamus.tabarato.designsystem.viewstate.StateViewArg
import br.com.hippopotamus.tabarato.designsystem.viewstate.StateViewType
import me.tigrao.github.repo.R
import me.tigrao.github.repo.presentation.model.RepoAction

internal class StateViewFactory(
    private val resources: Resources
) {

    fun emptyState() = StateViewArg(
        type = StateViewType.Empty(),
        title = resources.getString(R.string.repositories_state_empty_title),
        positiveButton = ButtonViewArg(
            text = resources.getString(R.string.repositories_state_empty_button_positive),
            action = RepoAction.TryAgain
        )
    )

    fun genericError() =
        StateViewArg(
            type = StateViewType.Empty(),
            title = resources.getString(R.string.repositories_state_generic_title),
            positiveButton = ButtonViewArg(
                text = resources.getString(R.string.repositories_state_generic_button_positive),
                action = RepoAction.TryAgain
            )
        )
}
