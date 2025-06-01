package com.example.kinopoisk.data.api.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kinopoisk.data.api.KinopoiskApi
import com.example.kinopoisk.data.model.MovieDto
import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.mappers.toDomain

class PremieresPagingSource(
    private val api: KinopoiskApi
): PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key?: 1
            val response = api.premieres(page)

            val nextKey = if (page >= response.totalPages) null else page + 1

            LoadResult.Page(
                data = response.items.map(MovieDto::toDomain),
                prevKey = if(page == 1) null else page - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}