package dev.tigrao.github.infra.network.di

import dagger.Module
import dagger.Provides
import dev.tigrao.github.infra.network.NetworkBuilder
import dev.tigrao.github.infra.network.NetworkService
import dev.tigrao.github.infra.network.OkhttpClientFactory
import dev.tigrao.github.infra.network.interceptor.AuthorizationInterceptor
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(networkBuilder: NetworkBuilder): Interceptor =
        AuthorizationInterceptor(networkBuilder)

    @Provides
    @Singleton
    fun provideApollo(
        networkBuilder: NetworkBuilder,
        okhttpClientFactory: OkhttpClientFactory,
    ) = NetworkService(
        networkBuilder = networkBuilder,
        okhttpClientFactory = okhttpClientFactory,
    ).createApolloService()

}
