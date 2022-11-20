package dev.tigrao.github.infra.network.di

import android.app.Application
import com.apollographql.apollo3.cache.normalized.sql.BuildConfig
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import dagger.multibindings.IntoSet
import dev.tigrao.github.infra.network.NetworkBuilder
import dev.tigrao.github.infra.network.NetworkService
import dev.tigrao.github.infra.network.OkhttpClientFactory
import dev.tigrao.github.infra.network.interceptor.AuthorizationInterceptor
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
object NetworkModule {

    private const val CACHE_NAME = "apollo.db"

    @Provides
    @IntoSet
    fun provideAuthInterceptor(networkBuilder: NetworkBuilder): Interceptor =
        AuthorizationInterceptor(networkBuilder)

    @Provides
    @ElementsIntoSet
    fun providesLoggerInterceptor(): Set<Interceptor> =
        if (BuildConfig.DEBUG) {
            setOf(HttpLoggingInterceptor())
        } else {
            emptySet<Interceptor>()
        }

    @Provides
    @Singleton
    internal fun provideApollo(
        networkBuilder: NetworkBuilder,
        okhttpClientFactory: OkhttpClientFactory,
        normalizedCacheFactory: SqlNormalizedCacheFactory,
    ) = NetworkService(
        networkBuilder = networkBuilder,
        okhttpClientFactory = okhttpClientFactory,
        sqlNormalizedCacheFactory = normalizedCacheFactory
    ).createApolloService()

    @Provides
    fun provideNormalizedCache(context: Application) =
        SqlNormalizedCacheFactory(context, CACHE_NAME)
}
