package com.example.kinopoisk.domain.usecases

import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopListUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<List<Movie>> {
        return repository.getTop()
    }
}