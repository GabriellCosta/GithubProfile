package de.tigrao.github.domain.profile.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.ApolloStore
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.apolloStore
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import de.tigrao.github.domain.profile.domain.ShouldExpireCache
import dev.tigrao.github.UserProfileQuery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetProfileDatasourceTest {

    val apolloClient = mockk<ApolloClient>()
    private val shouldExpireCache = mockk<ShouldExpireCache>()
    private val expireCacheDatasource = mockk<ExpireCacheDatasource>()

    val apolloStore = mockk<ApolloStore>()

    private val subject = GetProfileDatasource(
        apolloClient,
        shouldExpireCache,
        expireCacheDatasource,
    )

    @Before
    fun setup() {
        every {
            apolloClient.apolloStore
        } returns apolloStore

        every { apolloStore.clearAll() } returns true
    }

    @Test
    fun fetchProfileData_forced() = runBlocking {
        prepare()

        subject.fetchProfileData(true)

        coVerify {
            apolloClient
                .query(any<UserProfileQuery>())
                .fetchPolicy(FetchPolicy.CacheFirst)
                .execute()
                .dataAssertNoErrors
        }
    }

    fun prepare() {

    }
}
