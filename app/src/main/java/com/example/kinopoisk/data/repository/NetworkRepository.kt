package com.example.kinopoisk.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kinopoisk.data.api.KinopoiskApi
import com.example.kinopoisk.data.api.TopListPagingSource
import com.example.kinopoisk.data.model.Movie
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetworkRepository @Inject constructor(
    private val api: KinopoiskApi
): Repository {
    override fun getTop250Paged(): Flow<PagingData<Movie>> {
        return Pager(config = PagingConfig(pageSize = 20)) {
            TopListPagingSource(api)
        }.flow
    }

    override fun getPremieres(): Flow<List<Movie>> = flow {
        val response = api.premieres()
        emit(response.items)
    }

    override fun getPopular(): Flow<List<Movie>> = flow {
        val response = api.popular()
        emit(response.items)
    }

    override fun getTop250(): Flow<List<Movie>> = flow {
        val response = api.top250(1)
        emit(response.films)
    }

    override fun getSeries(): Flow<List<Movie>> = flow {
        val response = api.series()
        emit(response.items)
    }

    override fun getDynamicGenreCountryList(): Flow<List<Movie>> = flow {
//        val response = api.getMoviesByCountryAndGenre()
//        emit(response.films)
    }

    companion object {
    }
}