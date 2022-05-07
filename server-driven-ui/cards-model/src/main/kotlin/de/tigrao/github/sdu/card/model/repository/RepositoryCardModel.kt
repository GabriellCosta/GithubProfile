package de.tigrao.github.sdu.card.model.repository

data class RepositoryCardModel(
    val image: String,
    val name: String,
    val title: String,
    val description: String,
    val stars: String,
    val language: LanguageModel,
)

data class LanguageModel(
    val language: String,
    val color: String,
)
