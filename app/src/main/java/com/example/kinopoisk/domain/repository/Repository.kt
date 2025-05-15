package com.example.kinopoisk.domain.repository

import androidx.paging.PagingData
import com.example.kinopoisk.data.model.FiltersResponse
import com.example.kinopoisk.data.model.MovieDto
import com.example.kinopoisk.domain.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getFilters(): Flow<FiltersResponse>
    fun getPremieres(): Flow<List<Movie>>
    fun getPopular(): Flow<List<Movie>>
    fun getTop(): Flow<List<Movie>>
    fun getTopPaged(): Flow<PagingData<Movie>>
    fun getSeries(): Flow<List<Movie>>
    fun getDynamicGenreCountryList(countryId: Int, genreId: Int): Flow<List<Movie>>
}