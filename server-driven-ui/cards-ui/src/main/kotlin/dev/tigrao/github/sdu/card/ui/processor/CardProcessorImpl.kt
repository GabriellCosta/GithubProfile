package dev.tigrao.github.sdu.card.ui.processor

import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section
import de.tigrao.github.sdu.card.model.CardModel
import dev.tigrao.github.sdu.card.processor.CardProcessor
import javax.inject.Inject

internal class CardProcessorImpl @Inject constructor(
    private val cardToItemMapper: CardToItemMapper
) : CardProcessor {

    private val adapter = GroupieAdapter()

    override fun process(cards: List<CardModel>, recyclerView: RecyclerView) {

        val listOfItems = cardToItemMapper.mapFrom(cards, this)

        val section = Section().apply {
            listOfItems.forEach {
                add(it)
            }
        }

        adapter.add(section)

        recyclerView.adapter = adapter
    }
}
