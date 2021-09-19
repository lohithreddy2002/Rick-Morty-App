package com.example.rickmorty.utils

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "message")
    val error: String // this is the translated error shown to the user directly from the API
)
