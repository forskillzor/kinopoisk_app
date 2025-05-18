package com.example.kinopoisk.data.model

data class MovieDto(
    val kinopoiskId: Int,
    val nameRu: String?,
    val nameEn: String?,
    val year: String?,
    val filmLength: String?,
    val description: String?,
    val countries: List<CountryDto>,
    val genres: List<GenreDto>,
    val rating: String?,
    val ratingKinopoisk: String?,
    val ratingImdb: String?,
    val posterUrl: String,
    val posterUrlPreview: String
)

data class GenreDto(val id: Int, val genre: String)
data class CountryDto(val id: Int, val country: String)
