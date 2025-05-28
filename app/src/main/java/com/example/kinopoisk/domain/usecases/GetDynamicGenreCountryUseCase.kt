package com.example.kinopoisk.domain.usecases

import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.repository.MovieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetDynamicGenreCountryUseCase {

    operator fun invoke(): Flow<List<Movie>>

}

class GetDynamicGenreCountryUseCaseImpl @Inject constructor(
    private val repository: MovieRepository,
): GetDynamicGenreCountryUseCase {
    override operator fun invoke(): Flow<List<Movie>> = repository.getDynamic()
}