package de.tigrao.github.domain.profile

import com.apollographql.apollo3.ApolloClient
import de.tigrao.github.domain.profile.model.LanguageModel
import de.tigrao.github.domain.profile.model.RepositoryModel
import de.tigrao.github.domain.profile.model.UserProfileErrorModel
import de.tigrao.github.domain.profile.model.UserProfileModel
import deb.tigrao.github.infra.api.ResultDomain
import deb.tigrao.github.infra.api.callApi
import dev.tigrao.github.UserProfileQuery

interface GetProfileUseCase {
    suspend operator fun invoke(userName: String): ResultDomain<UserProfileModel, UserProfileErrorModel>
}

internal class GetProfileDefaultUseCase(
    private val apolloClient: ApolloClient
) : GetProfileUseCase {
    override suspend fun invoke(userName: String): ResultDomain<UserProfileModel, UserProfileErrorModel> {
        return callApi {
            apolloClient.query(UserProfileQuery()).execute().dataAssertNoErrors

        }.transformMap(success = { success ->
            success.user.let {
                UserProfileModel(
                    name = it.name,
                    nickName = it.bio.orEmpty(),
                    description = it.bio.orEmpty(),
                    followers = it.followers.totalCount,
                    following = it.following.totalCount,
                    pinnedRepos = it.pinnedItems.edges.map {
                        val item = it?.node?.onRepository!!

                        RepositoryModel(
                            name = item.name,
                            description = item.description.orEmpty(),
                            language = item.languages?.edges?.map {
                                it?.node?.let {
                                    LanguageModel(
                                        name = it.name,
                                        color = it.color!!,
                                    )
                                }
                            }?.firstOrNull(),
                            image = item.owner.avatarUrl.toString(),
                        )
                    },
                    topRepos = it.repositories.nodes?.map {
                        val item = it!!

                        RepositoryModel(
                            name = item.name,
                            description = item.description.orEmpty(),
                            language = item.languages?.nodes?.map {
                                it?.let {
                                    LanguageModel(
                                        name = it.name,
                                        color = it.color!!,
                                    )
                                }
                            }?.firstOrNull(),
                            image = item.owner.avatarUrl.toString(),
                        )
                    }.orEmpty(),
                )
            }
        }, error = {
            UserProfileErrorModel.GenericError
        })
    }

}
