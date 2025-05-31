package com.example.kinopoisk.data.model

import java.util.StringTokenizer

data class MovieDto(
    val kinopoiskId: Int,
    val nameRu: String?,
    val nameEn: String?,
    val year: Int?,
    val filmLength: Int?,
    val description: String?,
    val shortDescription: String?,
    val countries: List<CountryDto>?,
    val genres: List<GenreDto>?,
    val rating: String?,
    val ratingAgeLimits: String?,
    val ratingKinopoisk: String?,
    val ratingImdb: String?,
    val posterUrl: String,
    val posterUrlPreview: String,
    val coverUrl: String?,
    val logoUrl: String?
)

data class GenreDto(val id: Int, val genre: String)
data class CountryDto(val id: Int, val country: String)
