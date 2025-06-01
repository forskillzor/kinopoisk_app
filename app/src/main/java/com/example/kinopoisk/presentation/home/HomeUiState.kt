package com.example.kinopoisk.presentation.home

import com.example.kinopoisk.domain.entities.MovieSection

sealed interface HomeUiState {
    data object Loading: HomeUiState
    data class Success(val sections: List<MovieSection>): HomeUiState
    data class Error(val exception: Throwable): HomeUiState
}