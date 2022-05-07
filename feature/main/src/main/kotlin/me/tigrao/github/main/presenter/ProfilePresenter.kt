package me.tigrao.github.main.presenter

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
        fetchProfileData(DEFAULT_USER)
    }
}
