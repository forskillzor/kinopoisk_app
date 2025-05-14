package com.example.kinopoisk.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kinopoisk.data.api.KinopoiskApi
import com.example.kinopoisk.data.api.TopListPagingSource
import com.example.kinopoisk.data.model.FiltersResponse
import com.example.kinopoisk.data.model.Movie
import jakarta.inject.Inject
import jakarta.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import kotlin.random.Random

class NetworkRepository @Inject constructor(
    private val api: KinopoiskApi,
): Repository {
    override fun getTopPaged(): Flow<PagingData<Movie>> {
        return Pager(config = PagingConfig(pageSize = 20)) {
            TopListPagingSource(api)
        }.flow
    }

    override fun getFilters(): Flow<FiltersResponse> = flow {
        val response = api.getFilters()
        emit(response)
    }.flowOn(Dispatchers.IO)

    override fun getPremieres(): Flow<List<Movie>> = flow {
        val response = api.premieres()
        emit(response.items)
    }.flowOn(Dispatchers.IO)

    override fun getPopular(): Flow<List<Movie>> = flow {
        val response = api.popular()
        emit(response.items)
    }.flowOn(Dispatchers.IO)

    override fun getTop(): Flow<List<Movie>> = flow {
        val response = api.top(1)
        emit(response.films)
    }.flowOn(Dispatchers.IO)

    override fun getSeries(): Flow<List<Movie>> = flow {
        val response = api.series()
        emit(response.items)
    }.flowOn(Dispatchers.IO)

    override fun getDynamicGenreCountryList(countryId: Int, genreId: Int): Flow<List<Movie>> = flow {
        val filters = api.getFilters()
        val randomGenre = filters.genres.shuffled().first()
        val randomCountry = filters.countries.shuffled().first()

        val response = api.getMoviesByCountryAndGenre(
            // todo change random to filters
            countryId = countryId,
            genreId = genreId
        )
        emit(response.items)
    }.catch { e ->
        throw IOException("Не удалось получить фильмы по стране и жанру", e)
    }.flowOn(Dispatchers.IO)

    companion object {
    }
}