package com.example.kinopoisk.domain.repository

import androidx.paging.PagingData
import com.example.kinopoisk.data.model.FiltersResponse
import com.example.kinopoisk.data.model.MovieDto
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getFilters(): Flow<FiltersResponse>
    fun getPremieres(): Flow<List<MovieDto>>
    fun getPopular(): Flow<List<MovieDto>>
    fun getTop(): Flow<List<MovieDto>>
    fun getTopPaged(): Flow<PagingData<MovieDto>>
    fun getSeries(): Flow<List<MovieDto>>
    fun getDynamicGenreCountryList(countryId: Int, genreId: Int): Flow<List<MovieDto>>
}