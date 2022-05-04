package me.tigrao.github.repo.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class RepositoriesResponse(
    @Json(name = "total_count")
    val totalCount: Int,
    val items: List<RepositoryItemResponse>,
)
