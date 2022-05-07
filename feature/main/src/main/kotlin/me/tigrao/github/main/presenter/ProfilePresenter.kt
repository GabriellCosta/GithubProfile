package me.tigrao.github.main.presenter

import br.com.hippopotamus.tabarato.designsystem.viewstate.ButtonViewArg
import br.com.hippopotamus.tabarato.designsystem.viewstate.StateViewArg
import br.com.hippopotamus.tabarato.designsystem.viewstate.StateViewType
import br.com.tabarato.infra.action.dispatcher.ViewAction
import me.tigrao.github.main.domain.FetchProfileDataUseCase
import javax.inject.Inject

private const val DEFAULT_USER = "GabriellCosta"

internal class ProfilePresenter @Inject constructor(
    private val fetchProfileData: FetchProfileDataUseCase
) : ProfileContract.Presenter {

    private var view: ProfileContract.View? = null

    override fun attach(view: ProfileContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override suspend fun fetchProfileData() {
        fetchData(false)
    }

    override suspend fun forceRefresh() {
        fetchData(true)
    }

    private suspend fun fetchData(forced: Boolean) {
        fetchProfileData(DEFAULT_USER, forced).onSuccess {
            view?.setProfileData(it.cards)
        }.onError {
            view?.setErrorState(
                StateViewArg(
                    type = StateViewType.Api(),
                    title = "Error to Get data : (",
                    positiveButton = ButtonViewArg(
                        text = "Try Again", action = object : ViewAction {}
                    )
                )
            )
        }
    }
}
