package com.example.kinopoisk.domain.usecases

import androidx.paging.PagingData
import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.repository.MovieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetPopularPagedUseCase {

    operator fun  invoke(): Flow<PagingData<Movie>>

}

class GetPopularPagedUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository
): GetPopularPagedUseCase {
    override operator fun invoke(): Flow<PagingData<Movie>> = movieRepository.getPopularPaged()
}