package com.example.kinopoisk.domain.usecases

import androidx.paging.PagingData
import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.repository.MovieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetDynamicGenreCountryPagedUseCase {
    operator fun invoke(): Flow<PagingData<Movie>>
}

class GetDynamicGenreCountryPagedUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
): GetDynamicGenreCountryPagedUseCase {

    override fun invoke(): Flow<PagingData<Movie>> {
        return movieRepository.getDynamicGenreCountryListPaged()
    }
}