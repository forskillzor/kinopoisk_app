package com.example.kinopoisk.feature.homepage

import com.example.kinopoisk.data.model.MovieSection

sealed interface HomeUiState {
    data object Loading: HomeUiState
    data class Success(val sections: List<MovieSection>): HomeUiState
    data class Error(val exception: Throwable): HomeUiState
}