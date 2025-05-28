package com.example.kinopoisk.domain.usecases

import com.example.kinopoisk.domain.repository.MovieRepository
import jakarta.inject.Inject

interface RefreshCountryGenreSettingsUseCase{

    operator fun invoke()

}

class RefreshCountryGenreSettingsUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
): RefreshCountryGenreSettingsUseCase {
    override operator fun invoke() {
        repository.refreshRandomCountryAndGenre()
    }
}