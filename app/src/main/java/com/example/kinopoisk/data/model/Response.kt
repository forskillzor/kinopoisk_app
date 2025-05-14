package com.example.kinopoisk.data.model

data class Top250Response(val pagesCount: Int, val films: List<Movie>)
data class CollectionsResponse(val total: Int, val totalPages: Int, val items: List<Movie>)
data class FiltersResponse(val genres: List<GenreDto>, val countries: List<CountryDto>)
