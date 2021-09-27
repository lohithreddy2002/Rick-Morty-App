package com.example.rickmorty

import com.example.rickmorty.models.AllCharacters
import com.example.rickmorty.models.AllLocations
import com.example.rickmorty.models.Character
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("character")
    suspend fun getAllCharacters(): AllCharacters

    @GET("character")
    suspend fun getAllCharactersPage(
        @Query("page") page:Int
    ) :AllCharacters


    @GET("character/{id}")
    suspend fun getSingleCharacter(
        @Path("id") id: Int
    ): Character

    @GET("character/{id}")
    suspend fun getMultipleCharacters(
        @Path("id") id: Int
    ): List<Character>

    @GET("/location")
    suspend fun getAllLocations(): AllLocations
}
