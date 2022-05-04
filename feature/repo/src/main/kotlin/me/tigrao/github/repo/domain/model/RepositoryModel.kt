package me.tigrao.github.repo.domain.model

internal data class RepositoryModel(
    val data: List<RepositoryDataModel>,
)

internal data class RepositoryDataModel(
    val avatar: String,
    val title: String,
    val author: String,
    val stars: Int,
    val forks: Int,
    val description: String
)
