package dev.tigrao.github.sdu.card.ui.repository

import android.view.View
import com.bumptech.glide.Glide
import com.xwray.groupie.viewbinding.BindableItem
import de.tigrao.github.sdu.card.model.repository.RepositoryCardModel
import dev.tigrao.github.sdu.card.ui.R
import dev.tigrao.github.sdu.card.ui.databinding.CardsRepositoryBinding

internal class RepositoryCardItem(
    private val card: RepositoryCardModel,
) : BindableItem<CardsRepositoryBinding>() {

    override fun bind(viewBinding: CardsRepositoryBinding, position: Int) {
        with(viewBinding) {
            Glide.with(cardImage).load(card.image).into(cardImage)

            this.txtTitle.text = card.title
            this.txtName.text = card.name
            this.txtDescription.text = card.description

            this.txtStars.text = card.stars

            this.txtLanguage.text = card.language?.language
        }
    }

    override fun getLayout() = R.layout.cards_repository

    override fun initializeViewBinding(view: View) = CardsRepositoryBinding.bind(view)
}
