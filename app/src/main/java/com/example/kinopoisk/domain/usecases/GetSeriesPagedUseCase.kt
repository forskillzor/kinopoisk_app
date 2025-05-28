package com.example.kinopoisk.domain.usecases

import androidx.paging.PagingData
import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.repository.MovieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetSeriesPagedUseCase {

    operator fun invoke(): Flow<PagingData<Movie>>

}

class GetSeriesPagedUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
): GetSeriesPagedUseCase {
    override operator fun invoke(): Flow<PagingData<Movie>> = movieRepository.getSeriesPaged()
}