package com.example.rickmorty

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickmorty.models.AllCharacters
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
    fun getCharacters() = Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = { CharacterPagingSource(service) }).flow
}
