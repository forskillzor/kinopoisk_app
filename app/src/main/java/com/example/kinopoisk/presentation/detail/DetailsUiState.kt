package com.example.kinopoisk.presentation.detail

import com.example.kinopoisk.domain.entities.Movie

sealed interface DetailsUiState {
    data object Loading: DetailsUiState
    data class Success(val movie: Movie): DetailsUiState
    data class Error(val exception: Throwable): DetailsUiState
}
