package com.example.rickmorty

import com.example.rickmorty.models.AllCharacters
import com.example.rickmorty.models.Character
import com.example.rickmorty.utils.ResultWrapper
import com.example.rickmorty.utils.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    private val service: Service,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun getAllCharacters(): ResultWrapper<AllCharacters> {
        return safeApiCall(dispatcher) {
            service.getAllCharacters()
        }
    }

    suspend fun getCharacter() :ResultWrapper<Character>{
        return safeApiCall(dispatcher){
            service.getSingleCharacter(1)
        }
    }
}