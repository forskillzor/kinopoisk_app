package com.example.kinopoisk.presentation.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.data.model.MovieSection
import com.example.kinopoisk.data.model.SectionType
import com.example.kinopoisk.domain.usecases.GetDynamicGenreCountryUseCase
import com.example.kinopoisk.domain.usecases.GetPopularUseCase
import com.example.kinopoisk.domain.usecases.GetPremieresUseCase
import com.example.kinopoisk.domain.usecases.GetSeriesUseCase
import com.example.kinopoisk.domain.usecases.GetTopListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class HomeViewModel @Inject constructor(
    getPremieresUseCase: GetPremieresUseCase,
    getPopularUseCase: GetPopularUseCase,
    getTopListUseCase: GetTopListUseCase,
    getSeriesUseCase: GetSeriesUseCase,
    getDynamicGenreCountryUseCase: GetDynamicGenreCountryUseCase
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = combine(
            getPremieresUseCase(),
            getPopularUseCase(),
            getTopListUseCase(),
            getSeriesUseCase(),
            getDynamicGenreCountryUseCase()
        ) { premieres, popular, top250, series, dynamics ->
            listOf(
                MovieSection("Премьеры", premieres, SectionType.PREMIERES),
                MovieSection("Популярноое", popular, SectionType.POPULAR),
                MovieSection("Топ-250", top250, SectionType.TOP_250),
                MovieSection("Сериалы", series, SectionType.SERIES),
                MovieSection("Подборка", dynamics, SectionType.DYNAMIC_GENRE_COUNTRY)
            )
        }
        .map<List<MovieSection>, HomeUiState> { sections -> HomeUiState.Success(sections) }
        .catch<HomeUiState> { exception -> emit(HomeUiState.Error(exception)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState.Loading
        )

}