package com.example.kinopoisk.domain.usecases

import com.example.kinopoisk.data.model.Movie
import com.example.kinopoisk.data.repository.Repository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlin.random.Random

class GetDynamicGenreCountryUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<Movie>>
    = repository.getDynamicGenreCountryList(
        countryId = Random.nextInt(1, 5),
        genreId = Random.nextInt(1,5)
    )
}