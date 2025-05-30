package com.example.kinopoisk.domain.usecases

import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.repository.MovieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetMovieDetails {

    operator fun invoke(id: Int): Flow<Movie>

}

class GetMovieDetailsByMovieIdImpl @Inject constructor(
    private val repository: MovieRepository
): GetMovieDetails {
    override fun invoke(id: Int): Flow<Movie> {
        return  repository.getMovieByMovieId(id)
    }
}