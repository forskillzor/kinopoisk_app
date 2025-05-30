package com.example.kinopoisk.domain.mappers

import com.example.kinopoisk.data.model.MovieDto
import com.example.kinopoisk.domain.entities.Country
import com.example.kinopoisk.domain.entities.Genre
import com.example.kinopoisk.domain.entities.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = kinopoiskId,
        name = nameRu?: nameEn?: "",
        year = year?: 0,
        filmLength = filmLength?: 0,
        description = description?: "",
        countries = countries
            .map{country -> Country(country = country.country, id = country.id)},
        genres = genres
            .map{genre -> Genre(genre = genre.genre, id = genre.id)},
        rating = rating?: ratingKinopoisk?: ratingImdb,
        posterUrl = posterUrl,
        posterUrlPreview = posterUrlPreview,
        coverUrl = coverUrl?: "",
        logoUrl = logoUrl?: "",
        shortDescription = shortDescription,
        ratingAgeLimits = ratingAgeLimits
    )
}