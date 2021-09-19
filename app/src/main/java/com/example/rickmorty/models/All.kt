package com.example.rickmorty.models


import com.example.rickmorty.models.Info
import com.example.rickmorty.models.Result
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class all(
    @Json(name = "info")
    val info: Info,
    @Json(name = "results")
    val results: List<Result>
)