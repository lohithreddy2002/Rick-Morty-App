package com.example.rickmorty

import com.example.rickmorty.models.all
import retrofit2.Call
import retrofit2.http.GET

interface Service {

    @GET("character")
    suspend fun getAllCharacters(): all
}