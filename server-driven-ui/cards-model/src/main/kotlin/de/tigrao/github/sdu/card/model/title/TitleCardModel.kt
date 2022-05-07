package de.tigrao.github.sdu.card.model.title

data class TitleCardModel(
    val title: String,
    val action: ClickActionTitleModel,
)

data class ClickActionTitleModel(
    val text: String,
)
