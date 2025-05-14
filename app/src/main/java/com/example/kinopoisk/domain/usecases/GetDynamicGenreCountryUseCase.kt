package com.example.kinopoisk.domain.usecases

import com.example.kinopoisk.data.model.MovieDto
import com.example.kinopoisk.domain.repository.MovieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlin.random.Random

class GetDynamicGenreCountryUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<List<MovieDto>>
    = movieRepository.getDynamicGenreCountryList(
        countryId = Random.nextInt(1, 5),
        genreId = Random.nextInt(1,5)
    )
}