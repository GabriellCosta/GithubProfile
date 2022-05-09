package de.tigrao.github.domain.profile

import com.apollographql.apollo3.ApolloClient
import de.tigrao.github.domain.profile.model.LanguageModel
import de.tigrao.github.domain.profile.model.RepositoryModel
import de.tigrao.github.domain.profile.model.UserProfileErrorModel
import de.tigrao.github.domain.profile.model.UserProfileModel
import deb.tigrao.github.infra.api.ResultDomain
import deb.tigrao.github.infra.api.callApi
import dev.tigrao.github.UserProfileQuery
import dev.tigrao.github.fragment.LanguageFragment
import dev.tigrao.github.fragment.RepositoryFragment
import javax.inject.Inject

interface GetProfileUseCase {
    suspend operator fun invoke(userName: String): ResultDomain<UserProfileModel, UserProfileErrorModel>
}

internal class GetProfileDefaultUseCase @Inject constructor(
    private val apolloClient: ApolloClient
) : GetProfileUseCase {

    override suspend fun invoke(userName: String): ResultDomain<UserProfileModel, UserProfileErrorModel> {
        return callApi {
            apolloClient.query(UserProfileQuery()).execute().dataAssertNoErrors
        }.transformMap(success = { success ->
            success.user.let {
                UserProfileModel(
                    name = it.name,
                    image = it.avatarUrl.toString(),
                    nickName = it.login,
                    description = it.email.ifEmpty { it.bio.orEmpty() },
                    followers = it.followers.totalCount,
                    following = it.following.totalCount,
                    pinnedRepos = it.pinnedItems.edges.mapNotNull { edge ->
                        edge?.node?.repositoryFragment?.let(::mapFrom)
                    },
                    topRepos = it.repositories.nodes?.mapNotNull { edge ->
                        edge?.repositoryFragment?.let(::mapFrom)
                    }.orEmpty(),
                    starsRepo = it.starredRepositories.edges?.mapNotNull { edge ->
                        edge?.node?.repositoryFragment?.let(::mapFrom)
                    }.orEmpty()
                )
            }
        }, error = {
            UserProfileErrorModel.GenericError
        })
    }

    fun mapFrom(from: RepositoryFragment) : RepositoryModel =
        RepositoryModel(
            owner = from.owner.login,
            description = from.description.orEmpty(),
            language = mapFrom(from.primaryLanguage?.languageFragment),
            image = from.owner.avatarUrl.toString(),
            stars = from.stargazerCount,
            title = from.name
        )

    private fun mapFrom(from: LanguageFragment?) =
        from?.let {
            LanguageModel(
                name = it.name,
                color = it.color.orEmpty(),
            )
        }
}
