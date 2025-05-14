package com.example.kinopoisk.domain.usecases

import com.example.kinopoisk.data.model.MovieDto
import com.example.kinopoisk.domain.repository.MovieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetSeriesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke(): Flow<List<MovieDto>> = movieRepository.getSeries()
}