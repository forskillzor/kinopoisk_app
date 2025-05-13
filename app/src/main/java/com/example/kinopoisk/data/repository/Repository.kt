package com.example.kinopoisk.data.repository

import androidx.paging.PagingData
import com.example.kinopoisk.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getPremieres(): Flow<List<Movie>>
    fun getPopular(): Flow<List<Movie>>
    fun getTop(): Flow<List<Movie>>
    fun getTopPaged(): Flow<PagingData<Movie>>
    fun getSeries(): Flow<List<Movie>>
    fun getDynamicGenreCountryList(): Flow<List<Movie>>
}