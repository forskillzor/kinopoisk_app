package com.example.kinopoisk.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.kinopoisk.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreSettings (
    private val context: Context,
) : SettingsRepository {
    companion object {
        private val COUNTRY_ID = intPreferencesKey("country_id")
        private val GENRE_ID = intPreferencesKey("genre_id")
    }

    private val Context.dataStore by preferencesDataStore(name = "app_settings")
    override val countryGenrePair: Flow<Pair<Int, Int>> = context.dataStore.data.map { prefs ->
        Pair(
            prefs[COUNTRY_ID] ?: 1,
            prefs[GENRE_ID] ?: 1
        )
    }

    override suspend fun updateCountryGenre(countryId: Int, genreId: Int) {
        context.dataStore.edit { prefs ->
            prefs[COUNTRY_ID] = countryId
            prefs[GENRE_ID] = genreId
        }
    }
}