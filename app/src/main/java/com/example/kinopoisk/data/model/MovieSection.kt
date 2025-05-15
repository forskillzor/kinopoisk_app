package com.example.kinopoisk.data.model

import com.example.kinopoisk.domain.entities.Movie

data class MovieSection(
    val title: String,
    val movies: List<Movie>,
    val type: SectionType
)

enum class SectionType {
    PREMIERES,
    POPULAR,
    DYNAMIC_GENRE_COUNTRY,
    TOP_250,
    SERIES
}