package com.example.kinopoisk.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val countryGenrePair: Flow<Pair<Int, Int>> // countryId, genreId
    suspend fun updateCountryGenre(countryId: Int, genreId: Int)
}