package com.example.kinopoisk.domain.usecases

import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.repository.MovieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailsUseCase {

    operator fun invoke(id: Int): Flow<Movie>

}

class GetMovieDetailsByMovieIdImplUseCase @Inject constructor(
    private val repository: MovieRepository
): GetMovieDetailsUseCase {
    override fun invoke(id: Int): Flow<Movie> {
        return  repository.getMovieByMovieId(id)
    }
}