package com.example.kinopoisk.domain.mappers

import com.example.kinopoisk.data.model.MovieDto
import com.example.kinopoisk.domain.entities.Country
import com.example.kinopoisk.domain.entities.Genre
import com.example.kinopoisk.domain.entities.Movie

object DataToDomainMapper {
    fun map(movieDto: MovieDto): Movie{
        return Movie(
            id = movieDto.filmId,
            title = movieDto.nameRu?: movieDto.nameEn?: "",
            year = movieDto.year?: "",
            filmLength = movieDto.filmLength?: "",
            description = movieDto.description?: "",
            countries = movieDto.countries
                .map{country -> Country(country = country.country, id = country.id)},
            genres = movieDto.genres
                .map{genre -> Genre(genre = genre.genre, id = genre.id)},
            rating = movieDto.rating?: movieDto.ratingKinopoisk?: movieDto.ratingImdb,
            posterUrl = movieDto.posterUrl,
            posterUrlPreview = movieDto.posterUrlPreview
        )
    }
    fun map(movies: List<MovieDto>): List<Movie> {
        return movies.map(::map)
    }
}