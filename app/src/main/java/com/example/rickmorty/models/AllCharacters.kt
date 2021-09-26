package com.example.rickmorty.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllCharacters(
    @Json(name = "info")
    val info: Info?,
    @Json(name = "results")
    val results: List<Character>
)
