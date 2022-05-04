package de.tigrao.github.domain.profile.model

data class UserProfileModel(
    val name: String,
    val nickName: String,
    val description: String,
    val following: Int,
    val followers: Int,
    val pinnedRepos: List<RepositoryModel>,
    val topRepos: List<RepositoryModel>,
)

data class RepositoryModel(
    val name: String,
    val image: String,
    val language: LanguageModel?,
    val description: String,
)

data class LanguageModel(
    val name: String,
    val color: String,
)
