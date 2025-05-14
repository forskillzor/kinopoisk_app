package com.example.kinopoisk.domain.entities

data class Movie(
    val filmId: Int,
    val nameRu: String,
    val nameEn: String,
    val year: String,
    val filmLength: String,
    val countries: List<Country>,
    val genres: List<Genre>,
    val rating: String,
    val posterUrl: String,
    val posterUrlPreview: String
)

data class Genre(val id: Int, val genre: String)
data class Country(val id: Int, val country: String)
