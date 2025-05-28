package com.example.kinopoisk.domain.usecases

import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPopularUseCase {

    operator fun invoke(): Flow<List<Movie>>

}

class GetPopularUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
): GetPopularUseCase {
    override operator fun invoke(): Flow<List<Movie>> {
        return repository.getPopular()
    }
}