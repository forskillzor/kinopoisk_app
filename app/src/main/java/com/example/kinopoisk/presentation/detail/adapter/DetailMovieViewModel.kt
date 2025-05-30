package com.example.kinopoisk.presentation.detail.adapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.usecases.GetMovieDetails
import com.example.kinopoisk.presentation.detail.DetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val getMovieDetails: GetMovieDetails,
) : ViewModel() {
    var uiState: StateFlow<DetailsUiState> = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)

    fun  loadMovie(id: Int) {
        uiState = getMovieDetails(id)
            .map<Movie, DetailsUiState> { movie ->
                DetailsUiState.Success(movie)
            }
            .catch { e ->
                emit(DetailsUiState.Error(e))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = DetailsUiState.Loading
            )
    }
}