package dev.tigrao.github.sdu.card.ui.repository

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import dev.tigrao.github.sdu.card.ui.R
import dev.tigrao.github.sdu.card.ui.databinding.CardsRepositoryBinding

internal class RepositoryCardItem: BindableItem<CardsRepositoryBinding>() {

    override fun bind(viewBinding: CardsRepositoryBinding, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getLayout() = R.layout.cards_repository

    override fun initializeViewBinding(view: View) = CardsRepositoryBinding.bind(view)
}
