package com.example.kinopoisk.domain.entities

data class Movie(
    val id: Int,
    val name: String,
    val year: Int,
    val filmLength: Int,
    val description: String,
    val shortDescription: String?,
    val countries: List<Country>,
    val genres: List<Genre>,
    val rating: String?,
    val ratingAgeLimits: String?,
    val posterUrl: String,
    val posterUrlPreview: String,
    val coverUrl: String,
    val logoUrl: String
)

data class Genre(val id: Int, val genre: String)
data class Country(val id: Int, val country: String)
