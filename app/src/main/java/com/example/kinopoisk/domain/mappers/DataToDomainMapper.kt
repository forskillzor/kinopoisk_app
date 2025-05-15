package com.example.kinopoisk.domain.mappers

import com.example.kinopoisk.data.model.MovieDto
import com.example.kinopoisk.domain.entities.Country
import com.example.kinopoisk.domain.entities.Genre
import com.example.kinopoisk.domain.entities.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = filmId,
        title = nameRu?: nameEn?: "",
        year = year?: "",
        filmLength = filmLength?: "",
        description = description?: "",
        countries = countries
            .map{country -> Country(country = country.country, id = country.id)},
        genres = genres
            .map{genre -> Genre(genre = genre.genre, id = genre.id)},
        rating = rating?: ratingKinopoisk?: ratingImdb,
        posterUrl = posterUrl,
        posterUrlPreview = posterUrlPreview
    )
}