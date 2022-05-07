package dev.tigrao.github.sdu.card.ui.profile

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import dev.tigrao.github.sdu.card.ui.R
import dev.tigrao.github.sdu.card.ui.databinding.CardsUserProfileBinding

internal class ProfileCardItem(): BindableItem<CardsUserProfileBinding>() {

    override fun bind(viewBinding: CardsUserProfileBinding, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getLayout() = R.layout.cards_user_profile

    override fun initializeViewBinding(view: View) =
        CardsUserProfileBinding.bind(view)

}
