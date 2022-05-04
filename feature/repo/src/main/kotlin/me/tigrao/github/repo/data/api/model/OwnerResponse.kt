package me.tigrao.github.repo.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class OwnerResponse(
    @Json(name = "avatar_url")
    val avatar: String,
    val login: String
)
