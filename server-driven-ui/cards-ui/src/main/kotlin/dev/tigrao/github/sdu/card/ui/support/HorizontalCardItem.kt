package dev.tigrao.github.sdu.card.ui.support

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import de.tigrao.github.sdu.card.model.support.HorizontalCardModel
import dev.tigrao.github.sdu.card.processor.CardProcessor
import dev.tigrao.github.sdu.card.ui.R
import dev.tigrao.github.sdu.card.ui.databinding.CardsHorizontalBinding

internal class HorizontalCardItem(
    private val card: HorizontalCardModel,
    private val cardProcessor: CardProcessor,
) : BindableItem<CardsHorizontalBinding>() {

    override fun bind(viewBinding: CardsHorizontalBinding, position: Int) {
        cardProcessor.process(card.cards, viewBinding.recycler)
    }

    override fun getLayout() = R.layout.cards_horizontal

    override fun initializeViewBinding(view: View) = CardsHorizontalBinding.bind(view)
}
