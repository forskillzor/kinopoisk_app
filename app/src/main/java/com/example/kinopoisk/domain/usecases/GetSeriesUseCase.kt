package com.example.kinopoisk.domain.usecases

import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.repository.MovieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetSeriesUseCase {

    operator fun invoke(): Flow<List<Movie>>

}

class GetSeriesUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
): GetSeriesUseCase {
    override operator fun invoke(): Flow<List<Movie>> {
        return repository.getSeries()
    }
}