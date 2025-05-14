package com.example.kinopoisk.data.model

data class Top250Response(val pagesCount: Int, val films: List<MovieDto>)
data class CollectionsResponse(val total: Int, val totalPages: Int, val items: List<MovieDto>)
data class FiltersResponse(val genres: List<GenreDto>, val countries: List<CountryDto>)
