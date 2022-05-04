package me.tigrao.github.repo.data.api

import me.tigrao.github.repo.data.api.model.RepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RepoApi {

    @GET("search/repositories?q=language:kotlin&sort=stars")
    suspend fun fetchRepositoriesAsync(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): RepositoriesResponse
}
