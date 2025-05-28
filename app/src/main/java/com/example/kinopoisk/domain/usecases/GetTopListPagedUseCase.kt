package com.example.kinopoisk.domain.usecases

import androidx.paging.PagingData
import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.repository.MovieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetTopListPagedUseCase {

    operator fun invoke(): Flow<PagingData<Movie>>

}

class GetTopListPagedUseCaseImpl @Inject constructor(
    val movieRepository: MovieRepository
): GetTopListPagedUseCase {
    override operator fun invoke(): Flow<PagingData<Movie>> = movieRepository.getTopPaged()

}