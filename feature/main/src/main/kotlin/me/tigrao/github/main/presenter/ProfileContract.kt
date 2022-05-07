package me.tigrao.github.main.presenter

import br.com.hippopotamus.tabarato.designsystem.viewstate.StateViewArg
import de.tigrao.github.sdu.card.model.CardModel

interface ProfileContract {

    interface View {
        fun setProfileData(): List<CardModel>

        fun setErrorState(): StateViewArg
    }

    interface Presenter {
        fun attach(view: View)

        fun detach()

        suspend fun fetchProfileData()
    }
}
