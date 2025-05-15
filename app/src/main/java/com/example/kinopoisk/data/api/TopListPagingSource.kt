package com.example.kinopoisk.data.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoisk.data.model.MovieDto

class TopListPagingSource(
    private val api: KinopoiskApi
    // todo change MovieDto to Movie uses DataToDomainMapper
): PagingSource<Int, MovieDto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        val page = params.key?: 1
        val response = api.top(page)
        return LoadResult.Page(
            // todo add mapping here?
            data = response.films,
            prevKey = if(page == 1) null else page - 1,
            nextKey = page + 1
        )
    }
    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }

}