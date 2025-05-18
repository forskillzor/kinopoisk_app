package com.example.kinopoisk.domain.usecases

import androidx.paging.PagingData
import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.repository.MovieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlin.random.Random

class GetDynamicGenreCountryPagedUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>>
    = movieRepository.getDynamicGenreCountryListPaged(
        countryId = Random.nextInt(1, 5),
        genreId = Random.nextInt(1,5)
    )
}