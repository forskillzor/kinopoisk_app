package com.example.kinopoisk.domain.entities

// todo move this to presentation
data class MovieSection(
    var title: String,
    val movies: List<Movie>,
    val type: SectionType
) {
    fun getName(): String = "${movies.first().countries?.first()?.country} ${movies.first().genres?.first()?.genre}"
}

enum class SectionType {
    PREMIERES,
    POPULAR,
    DYNAMIC_GENRE_COUNTRY,
    TOP_250,
    SERIES,
}