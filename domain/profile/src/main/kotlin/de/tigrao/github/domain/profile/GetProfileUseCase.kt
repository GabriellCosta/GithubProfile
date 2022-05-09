package de.tigrao.github.domain.profile

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import de.tigrao.github.domain.profile.mapper.ProfileErrorMapper
import de.tigrao.github.domain.profile.mapper.ProfileSuccessMapper
import de.tigrao.github.domain.profile.model.UserProfileErrorModel
import de.tigrao.github.domain.profile.model.UserProfileModel
import deb.tigrao.github.infra.api.ResultDomain
import deb.tigrao.github.infra.api.callApi
import dev.tigrao.github.UserProfileQuery
import javax.inject.Inject

interface GetProfileUseCase {
    suspend operator fun invoke(userName: String): ResultDomain<UserProfileModel, UserProfileErrorModel>
}

internal class GetProfileDefaultUseCase @Inject constructor(
    private val apolloClient: ApolloClient,
    private val successMapper: ProfileSuccessMapper,
    private val errorMapper: ProfileErrorMapper,
) : GetProfileUseCase {

    override suspend fun invoke(userName: String): ResultDomain<UserProfileModel, UserProfileErrorModel> {
        return callApi {
            apolloClient
                .query(UserProfileQuery())
                .fetchPolicy(FetchPolicy.CacheFirst)
                .execute()
                .dataAssertNoErrors
        }.transformMap(
            successMapper::mapFrom,
            errorMapper::mapFrom,
        )
    }
}
