package com.example.kinopoisk.data.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoisk.data.model.Movie

class TopListPagingSource(
    private val api: KinopoiskApi
): PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key?: 1
        val response = api.top(page)
        return LoadResult.Page(
            data = response.films,
            prevKey = if(page == 1) null else page - 1,
            nextKey = page + 1
        )
    }
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

}