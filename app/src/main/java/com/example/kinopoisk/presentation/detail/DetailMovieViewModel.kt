package com.example.kinopoisk.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.entities.Staff
import com.example.kinopoisk.domain.usecases.GetMovieDetailsUseCase
import com.example.kinopoisk.domain.usecases.GetStaffByMovieIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getStaffByMovieIdUseCase: GetStaffByMovieIdUseCase
) : ViewModel() {
    var uiState: StateFlow<DetailsUiState> =
        MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)

    var staffList: MutableStateFlow<List<Staff>> = MutableStateFlow(emptyList())

    fun  loadMovie(id: Int) {
        uiState = getMovieDetailsUseCase(id)
            .map<Movie, DetailsUiState> { movie ->
                DetailsUiState.Success(movie)
            }
            .catch { e ->
                emit(DetailsUiState.Error(e))
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Companion.WhileSubscribed(5000),
                initialValue = DetailsUiState.Loading
            )
    }

    fun loadActors(id: Int) {
        viewModelScope.launch {
            getStaffByMovieIdUseCase(id).collect { staff ->
                staffList.value = staff
            }
        }
    }
}