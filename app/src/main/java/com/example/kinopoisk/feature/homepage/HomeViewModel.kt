package com.example.kinopoisk.feature.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.data.repository.Repository
import com.example.kinopoisk.data.model.MovieSection
import com.example.kinopoisk.data.model.SectionType
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadSections()
    }

    private fun loadSections() {
        viewModelScope.launch {
            runCatching {
                val premieres = repository.getPremieres().first()
                val popular = repository.getPopular().first()
                val top250 = repository.getTop250().first()
                val series = repository.getSeries().first()
                listOf(
                    MovieSection("Премьеры", premieres, SectionType.PREMIERES),
                    MovieSection("Популярноое", popular, SectionType.POPULAR),
                    MovieSection("Топ-250", top250, SectionType.TOP_250),
                    MovieSection("Сериалы", series, SectionType.SERIES),
                )
            }.onSuccess { sections ->
                _uiState.value = HomeUiState.Success(sections)
            }.onFailure { error ->
                _uiState.value = HomeUiState.Error(error)
            }
        }
    }
}