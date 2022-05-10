package de.tigrao.github.domain.profile.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.apolloStore
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import de.tigrao.github.domain.profile.domain.ShouldExpireCache
import dev.tigrao.github.UserProfileQuery
import javax.inject.Inject

internal class GetProfileDatasource @Inject constructor(
    private val apolloClient: ApolloClient,
    private val shouldExpireCache: ShouldExpireCache,
    private val expireCacheDatasource: ExpireCacheDatasource,
) {

    suspend fun fetchProfileData(
        force: Boolean
    ): UserProfileQuery.Data {
        if (force || shouldExpireCache()) {
            apolloClient.apolloStore.clearAll()
            expireCacheDatasource.deleteAndSaveTime()
        }

        return apolloClient
            .query(UserProfileQuery())
            .fetchPolicy(FetchPolicy.CacheFirst)
            .execute()
            .dataAssertNoErrors
    }
}
