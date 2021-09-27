package com.example.rickmorty

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickmorty.models.Character
import timber.log.Timber


class test(val service: Service) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let {
            val anchorpage = state.closestPageToPosition(it)
            anchorpage?.prevKey?.plus(1) ?: anchorpage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val nextpage = params.key ?: 1
        val response = service.getAllCharactersPage(nextpage)
        Timber.e(response.info?.next?.get(response.info.next.length - 1).toString())
        return LoadResult.Page(
            data = response.results,
            prevKey = null,
            nextKey = response.info?.next?.get(response.info.next.length - 1)
                .toString().toInt()
        )

    }
}