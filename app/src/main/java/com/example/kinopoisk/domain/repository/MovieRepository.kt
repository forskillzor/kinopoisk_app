package com.example.kinopoisk.domain.repository

import androidx.paging.PagingData
import com.example.kinopoisk.data.model.FiltersResponse
import com.example.kinopoisk.data.model.MovieDto
import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.entities.Staff
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    // movies
    fun getFilters(): Flow<FiltersResponse>
    fun getPremieres(): Flow<List<Movie>>
    fun getPopular(): Flow<List<Movie>>
    fun getTop(): Flow<List<Movie>>
    fun getSeries(): Flow<List<Movie>>
    fun getDynamic(): Flow<List<Movie>>

    //  movies paging data
    fun getPremieresPaged(): Flow<PagingData<Movie>>
    fun getPopularPaged(): Flow<PagingData<Movie>>
    fun getTopPaged(): Flow<PagingData<Movie>>
    fun getSeriesPaged(): Flow<PagingData<Movie>>
    fun getDynamicGenreCountryListPaged(): Flow<PagingData<Movie>>
    fun refreshRandomCountryAndGenre()
    fun getMovieByMovieId(id: Int): Flow<Movie>

    //  staff
    fun getStaffByMovieId(id: Int): Flow<List<Staff>>
}