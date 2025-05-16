package com.example.kinopoisk.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kinopoisk.domain.entities.Country
import com.example.kinopoisk.domain.entities.Genre


@Entity(tableName = "movies")
data class MovieEntity (
    @PrimaryKey val id: Int,
    val title: String,
    val year: String,
    val filmLength: String,
    val description: String,
    val countries: List<Country>,
    val genres: List<Genre>,
    val rating: String?,
    val posterUrl: String,
    val posterUrlPreview: String,
    val isWatched: Boolean,
    val isInCollection: Boolean
)