package com.example.kinopoisk.data.model

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

data class Genre(val genre: String)
data class Country(val country: String)

data class MovieResponse(val pagesCount: Int, val films: List<Movie>)