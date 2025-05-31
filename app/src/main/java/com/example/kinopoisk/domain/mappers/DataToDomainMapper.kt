package com.example.kinopoisk.domain.mappers

import com.example.kinopoisk.data.model.MovieDto
import com.example.kinopoisk.data.model.StaffDto
import com.example.kinopoisk.data.model.StaffTypeDto
import com.example.kinopoisk.domain.entities.Country
import com.example.kinopoisk.domain.entities.Genre
import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.entities.Staff
import com.example.kinopoisk.domain.entities.StaffType

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = kinopoiskId,
        name = nameRu ?: nameEn ?: "",
        year = year ?: 0,
        filmLength = filmLength ?: 0,
        description = description ?: "",
        countries = countries
            .map { country -> Country(country = country.country, id = country.id) },
        genres = genres
            .map { genre -> Genre(genre = genre.genre, id = genre.id) },
        rating = rating ?: ratingKinopoisk ?: ratingImdb,
        posterUrl = posterUrl,
        posterUrlPreview = posterUrlPreview,
        coverUrl = coverUrl ?: "",
        logoUrl = logoUrl ?: "",
        shortDescription = shortDescription,
        ratingAgeLimits = ratingAgeLimits
    )
}

fun StaffDto.toDomain(): Staff {
    return Staff(
        id = staffId,
        name = nameRu,
        description = description ?: "",
        posterUrl = posterUrl,
        professionText = professionText,
        professionKey = professionKey.toDomain()
    )
}

fun StaffTypeDto.toDomain(): StaffType = when (this) {
    StaffTypeDto.WRITER -> StaffType.WRITER
    StaffTypeDto.OPERATOR -> StaffType.OPERATOR
    StaffTypeDto.EDITOR -> StaffType.EDITOR
    StaffTypeDto.COMPOSER -> StaffType.COMPOSER
    StaffTypeDto.PRODUCER_USSR -> StaffType.PRODUCER_USSR
    StaffTypeDto.TRANSLATOR -> StaffType.TRANSLATOR
    StaffTypeDto.DIRECTOR -> StaffType.DIRECTOR
    StaffTypeDto.DESIGN -> StaffType.DESIGN
    StaffTypeDto.PRODUCER -> StaffType.PRODUCER
    StaffTypeDto.ACTOR -> StaffType.ACTOR
    StaffTypeDto.VOICE_DIRECTOR -> StaffType.VOICE_DIRECTOR
    StaffTypeDto.UNKNOWN -> StaffType.UNKNOWN
}