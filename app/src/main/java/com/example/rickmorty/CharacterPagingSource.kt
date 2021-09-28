package com.example.rickmorty

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickmorty.models.Character
import kotlinx.coroutines.delay
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class CharacterPagingSource(private val service: Service) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let {
            val anchorpage = state.closestPageToPosition(it)
            anchorpage?.prevKey?.plus(1) ?: anchorpage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val nextpage = params.key ?: 1

        return try {

            val response = service.getAllCharactersPage(nextpage)
            val number = response.info?.next?.let { Regex("[0-9]+").find(it)?.value }
            Timber.e("$number")
            delay(2000)
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = number?.toInt()
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}
