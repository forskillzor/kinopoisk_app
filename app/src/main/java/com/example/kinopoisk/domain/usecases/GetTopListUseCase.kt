package com.example.kinopoisk.domain.usecases

import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetTopListUseCase {

    operator fun invoke(): Flow<List<Movie>>

}

class GetTopListUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
): GetTopListUseCase {
    override operator fun invoke(): Flow<List<Movie>> {
        return repository.getTop()
    }
}