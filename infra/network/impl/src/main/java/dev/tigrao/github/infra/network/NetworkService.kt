package dev.tigrao.github.infra.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import javax.inject.Inject

internal class NetworkService @Inject constructor(
    private val networkBuilder: NetworkBuilder,
    private val okhttpClientFactory: OkhttpClientFactory,
) {

    fun createApolloService() = ApolloClient.Builder()
        .serverUrl(networkBuilder.baseUrl)
        .okHttpClient(okhttpClientFactory.createNewInstance())
        .build()
}
