package dev.tigrao.github.sdu.card.ui.title

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import dev.tigrao.github.sdu.card.ui.R
import dev.tigrao.github.sdu.card.ui.databinding.CardsTitleBinding

internal class TitleCardItem : BindableItem<CardsTitleBinding>() {

    override fun bind(viewBinding: CardsTitleBinding, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getLayout() = R.layout.cards_title

    override fun initializeViewBinding(view: View) = CardsTitleBinding.bind(view)
}
