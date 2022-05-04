package me.tigrao.github.repo.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class RepositoryItemResponse(
    val id: Long,
    val name: String,
    val description: String?,
    val owner: OwnerResponse,
    @Json(name = "forks_count")
    val forks: Int,
    @Json(name = "stargazers_count")
    val stars: Int
)
