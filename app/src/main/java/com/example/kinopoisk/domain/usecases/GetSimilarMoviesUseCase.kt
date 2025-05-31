package com.example.kinopoisk.domain.usecases

import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.repository.MovieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetSimilarMoviesUseCase {

    operator fun invoke(id: Int): Flow<List<Movie>>

}

class GetSimilarMoviesUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
): GetSimilarMoviesUseCase {
    override fun invoke(id: Int): Flow<List<Movie>> {
        return repository.getSimilarMovies(id)
    }
}