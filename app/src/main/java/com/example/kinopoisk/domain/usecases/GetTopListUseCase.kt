package com.example.kinopoisk.domain.usecases

import com.example.kinopoisk.data.repository.Repository
import com.example.kinopoisk.data.model.Movie
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow


class GetTopListUseCase @Inject constructor(val repository: Repository) {
    operator fun invoke(): Flow<List<Movie>> = repository.getTop()

}