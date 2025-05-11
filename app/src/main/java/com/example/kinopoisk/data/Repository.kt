package com.example.kinopoisk.data

import androidx.paging.PagingData
import com.example.kinopoisk.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getPremieres(): Flow<List<Movie>>
    fun getPopular(): Flow<List<Movie>>
    fun getTop250(): Flow<List<Movie>>
    fun getTop250Paged(): Flow<PagingData<Movie>>
    fun getSeries(): Flow<List<Movie>>
    fun getDynamicGenreCountryList(): Flow<List<Movie>>
}