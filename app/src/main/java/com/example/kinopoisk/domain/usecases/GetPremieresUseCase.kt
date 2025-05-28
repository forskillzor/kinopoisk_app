package com.example.kinopoisk.domain.usecases

import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPremieresUseCase {

    operator fun invoke(): Flow<List<Movie>>

}

class GetPremieresUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
): GetPremieresUseCase {
    override operator fun invoke(): Flow<List<Movie>> {
        return repository.getPremieres()
    }
}