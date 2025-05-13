package com.example.kinopoisk.domain.usecases

import androidx.paging.PagingData
import com.example.kinopoisk.data.model.Movie
import com.example.kinopoisk.data.repository.Repository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetTopListPagedUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(): Flow<PagingData<Movie>> = repository.getTopPaged()
}