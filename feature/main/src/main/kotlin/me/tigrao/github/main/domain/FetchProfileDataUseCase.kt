package me.tigrao.github.main.domain

import de.tigrao.github.domain.profile.GetProfileUseCase
import de.tigrao.github.domain.profile.model.RepositoryModel
import de.tigrao.github.sdu.card.model.CardModel
import de.tigrao.github.sdu.card.model.profile.ProfileCardModel
import de.tigrao.github.sdu.card.model.repository.LanguageModel
import de.tigrao.github.sdu.card.model.repository.RepositoryCardModel
import de.tigrao.github.sdu.card.model.support.HorizontalCardModel
import de.tigrao.github.sdu.card.model.title.ClickActionTitleModel
import de.tigrao.github.sdu.card.model.title.TitleCardModel
import me.tigrao.github.main.domain.model.UserProfileUiErrorModel
import me.tigrao.github.main.domain.model.UserProfileUiModel

internal interface FetchProfileDataUseCase {
    suspend operator fun invoke(userName: String)
}

internal class FetchProfileDataUseCaseDefault(
    private val getProfileDataUseCase: GetProfileUseCase,
): FetchProfileDataUseCase {
    override suspend fun invoke(userName: String) {
        getProfileDataUseCase(userName)
            .map(
                success = {

                    val responseList = mutableListOf<CardModel>()

                    val profileCard: CardModel = ProfileCardModel(
                        image = it.image,
                        title = it.name,
                        subtitle = it.nickName,
                        contact = it.description,
                        bottomText = "${it.followers} followers ${it.following} following"
                    )
                    responseList.add(profileCard)

                    val pinnedTitle: CardModel = TitleCardModel(
                        title = "Pinned",
                        action = ClickActionTitleModel(
                            text = "View all",
                        )
                    )
                    responseList.add(pinnedTitle)

                    it.pinnedRepos.map { repo ->
                        responseList.add(mapRepo(repo))
                    }

                    val topTitle: CardModel = TitleCardModel(
                        title = "Top repositories",
                        action = ClickActionTitleModel(
                            text = "View all",
                        )
                    )
                    responseList.add(topTitle)

                    val topRepositories: List<CardModel> = it.topRepos.map(::mapRepo)
                    responseList.add(HorizontalCardModel(topRepositories))

                    val starredTitle: CardModel = TitleCardModel(
                        title = "Starred repositories",
                        action = ClickActionTitleModel(
                            text = "View all",
                        )
                    )

                    UserProfileUiModel(responseList)
                },
                error = {
                    UserProfileUiErrorModel.GenericError
                }
            )
    }

    private fun mapRepo(repo: RepositoryModel): CardModel =
        RepositoryCardModel(
            image = repo.image,
            name = repo.owner,
            title = repo.owner,
            description = repo.description,
            stars = repo.stars.toString(),
            language = repo.language?.let { language ->
                LanguageModel(
                    language = language.name,
                    color = language.color,
                )
            }
        )
}
