package com.example.kinopoisk.domain.usecases

import com.example.kinopoisk.data.model.Movie
import com.example.kinopoisk.data.repository.Repository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetPremieresUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(): Flow<List<Movie>> = repository.getPremieres()
}