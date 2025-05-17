package com.example.kinopoisk.data.local

import androidx.room.TypeConverter
import com.example.kinopoisk.domain.entities.Country
import com.example.kinopoisk.domain.entities.Genre
import kotlinx.serialization.json.Json

class MovieConverters {

    @TypeConverter
    fun fromCountryList(value: List<Country>): String =
        Json.encodeToString(value)

    @TypeConverter
    fun toCountryList(value: String): List<Country> =
        Json.decodeFromString(value)

    @TypeConverter
    fun fromGereList(value: List<Genre>): String =
        Json.encodeToString(value)

    @TypeConverter
    fun toGenreList(value: String): List<Genre> =
        Json.decodeFromString(value)
}