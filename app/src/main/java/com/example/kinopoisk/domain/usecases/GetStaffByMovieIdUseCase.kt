package com.example.kinopoisk.domain.usecases

import com.example.kinopoisk.domain.entities.Staff
import com.example.kinopoisk.domain.repository.MovieRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetStaffByMovieIdUseCase {

    operator fun invoke(id: Int): Flow<List<Staff>>

}

class GetStaffByMovieIdUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
): GetStaffByMovieIdUseCase{
    override fun invoke(id: Int): Flow<List<Staff>> {
        return repository.getStaffByMovieId(id)
    }
}