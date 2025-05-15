package com.example.kinopoisk.domain.usecases

import androidx.paging.PagingData
import com.example.kinopoisk.data.model.MovieDto
import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.repository.MovieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetTopListPagedUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(): Flow<PagingData<Movie>> = repository.getTopPaged()
}