package com.example.kinopoisk.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kinopoisk.data.api.KinopoiskApi
import com.example.kinopoisk.data.api.pagingsource.DynamicPagingSource
import com.example.kinopoisk.data.api.pagingsource.PopularPagingSource
import com.example.kinopoisk.data.api.pagingsource.PremieresPagingSource
import com.example.kinopoisk.data.api.pagingsource.SeriesPagingSource
import com.example.kinopoisk.data.api.pagingsource.TopListPagingSource
import com.example.kinopoisk.data.local.MovieDao
import com.example.kinopoisk.data.model.CollectionsResponse
import com.example.kinopoisk.data.model.FiltersResponse
import com.example.kinopoisk.data.model.MovieDto
import com.example.kinopoisk.data.model.Top250Response
import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.mappers.toDomain
import com.example.kinopoisk.domain.repository.MovieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class MovieRepositoryImpl @Inject constructor(
    private val api: KinopoiskApi,
    private val movieDao: MovieDao,
    private val dataStore: DataStore<Preferences>
) : MovieRepository {

    override fun getFilters(): Flow<FiltersResponse> = flow {
        val response = api.getFilters()
        emit(response)
    }.flowOn(Dispatchers.IO)

    override fun getPremieres(): Flow<List<Movie>> = fetchMovies { api.premieres() }
    override fun getPopular(): Flow<List<Movie>> = fetchMovies { api.popular() }
    override fun getTop(): Flow<List<Movie>> = fetchMovies { api.top() }
    override fun getSeries(): Flow<List<Movie>> = fetchMovies { api.series() }
    override fun getDynamic(countryId: Int, genreId: Int): Flow<List<Movie>> =
        fetchMovies { api.dynamic(countryId, genreId) }

    override fun getTopPaged(): Flow<PagingData<Movie>> =
        Pager(config = PagingConfig(pageSize = 20)) {
            TopListPagingSource(api)
        }.flow

    override fun getPremieresPaged(): Flow<PagingData<Movie>> =
        Pager(config = PagingConfig(pageSize = 20)) {
            PremieresPagingSource(api)
        }.flow

    override fun getPopularPaged(): Flow<PagingData<Movie>> =
        Pager(config = PagingConfig(pageSize = 20)) {
            PopularPagingSource(api)
        }.flow

    override fun getSeriesPaged(): Flow<PagingData<Movie>> =
        Pager(config = PagingConfig(pageSize = 20)) {
            SeriesPagingSource(api)
        }.flow

    override fun getDynamicGenreCountryListPaged(
        countryId: Int,
        genreId: Int
    ): Flow<PagingData<Movie>> =
        Pager(config = PagingConfig(pageSize = 20)) {
            DynamicPagingSource(api, countryId, genreId)
        }.flow
}

private inline fun <reified T> fetchMovies(
    crossinline apiCall: suspend () -> T
): Flow<List<Movie>> where T : Any {
    return flow {
        val response = apiCall.invoke()
        val movieDao = extractMoviesFromResponse(response)
        emit(movieDao.map(MovieDto::toDomain))
    }.flowOn(Dispatchers.IO)
}

private fun extractMoviesFromResponse(response: Any): List<MovieDto> {
    return when (response) {
        is CollectionsResponse -> response.items
        is Top250Response -> response.films
        else -> emptyList()
    }
}
