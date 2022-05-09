package de.tigrao.github.domain.profile.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.apolloStore
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import dev.tigrao.github.UserProfileQuery
import javax.inject.Inject

internal class GetProfileDatasource @Inject constructor(
    private val apolloClient: ApolloClient,
) {

    suspend fun fetchProfileData(
        force: Boolean
    ): UserProfileQuery.Data {
        if (force) {
            apolloClient.apolloStore.clearAll()
        }

        return apolloClient
            .query(UserProfileQuery())
            .fetchPolicy(FetchPolicy.CacheFirst)
            .execute()
            .dataAssertNoErrors
    }
}
