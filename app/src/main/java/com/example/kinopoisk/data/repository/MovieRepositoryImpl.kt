package com.example.kinopoisk.data.repository

import android.util.Log
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
import com.example.kinopoisk.domain.entities.Staff
import com.example.kinopoisk.domain.mappers.toDomain
import com.example.kinopoisk.domain.repository.MovieRepository
import com.example.kinopoisk.domain.repository.SettingsRepository
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.random.Random


class MovieRepositoryImpl @Inject constructor(
    private val api: KinopoiskApi,
    private val movieDao: MovieDao,
    private val dataStore: DataStore<Preferences>,
    private val settings: SettingsRepository
) : MovieRepository {
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    override fun getFilters(): Flow<FiltersResponse> = flow {
        val response = api.getFilters()
        emit(response)
    }.flowOn(Dispatchers.IO)

    override fun getPremieres(): Flow<List<Movie>> = fetchMovies { api.premieres() }
    override fun getPopular(): Flow<List<Movie>> = fetchMovies { api.popular() }
    override fun getTop(): Flow<List<Movie>> = fetchMovies { api.top() }
    override fun getSeries(): Flow<List<Movie>> = fetchMovies { api.series() }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getDynamic(): Flow<List<Movie>> =
        settings.countryGenrePair.map { (countryId, genreId) ->
            fetchMovies { api.dynamic(countryId, genreId) }
        }.flattenMerge()

    override fun getSimilarMovies(id: Int): Flow<List<Movie>> =
        fetchMovies { api.getSimilarMovies(id) }

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

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getDynamicGenreCountryListPaged(): Flow<PagingData<Movie>> =
        settings.countryGenrePair.map { (countryId, genreId) ->
            Pager(config = PagingConfig(pageSize = 20)) {
                DynamicPagingSource(api, countryId, genreId)
            }.flow
        }.flattenMerge()

    override fun refreshRandomCountryAndGenre() {
        val newCountryId = Random.nextInt(1, 5)
        val newGenreId = Random.nextInt(1, 5)
        Log.d("TAAG", "set to datastore country: $newCountryId, genre: $newGenreId()")
        scope.launch(Dispatchers.IO) {
            settings.updateCountryGenre(newCountryId, newGenreId)
        }
    }

    override fun getMovieByMovieId(id: Int): Flow<Movie> {
        return flow {
            val movieDto = api.getFilmById(id)
            emit(movieDto.toDomain())
        }.flowOn(Dispatchers.IO)
    }

    override fun getStaffByMovieId(id: Int): Flow<List<Staff>> {
        return flow {
            val staffList = api.getStaffByMovieId(id).map { item -> item.toDomain() }
            emit(staffList)
        }.flowOn(Dispatchers.IO)
    }
}

private inline fun <reified T> fetchMovies(
    crossinline apiCall: suspend () -> T
): Flow<List<Movie>> where T : Any {
    return flow {
        val response = apiCall.invoke()
        val movieDaoList = extractMoviesFromResponse(response)
        if (movieDaoList.isNotEmpty()) {
            emit(movieDaoList.map(MovieDto::toDomain))
        }
    }.flowOn(Dispatchers.IO)
}

private fun extractMoviesFromResponse(response: Any): List<MovieDto> {
    return when (response) {
        is CollectionsResponse -> response.items
        is Top250Response -> response.films
        else -> emptyList()
    }
}

