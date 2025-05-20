package com.example.kinopoisk.domain.usecases

import com.example.kinopoisk.domain.repository.MovieRepository
import jakarta.inject.Inject

class RefreshCountryGenreSettingsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke() {
        repository.refreshRandomCountryAndGenre()
    }
}