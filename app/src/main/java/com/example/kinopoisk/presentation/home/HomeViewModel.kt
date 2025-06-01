package com.example.kinopoisk.presentation.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk.R
import com.example.kinopoisk.domain.entities.MovieSection
import com.example.kinopoisk.domain.entities.SectionType
import com.example.kinopoisk.domain.usecases.GetDynamicGenreCountryUseCase
import com.example.kinopoisk.domain.usecases.GetPopularUseCase
import com.example.kinopoisk.domain.usecases.GetPremieresUseCase
import com.example.kinopoisk.domain.usecases.GetSeriesUseCase
import com.example.kinopoisk.domain.usecases.GetTopListUseCase
import com.example.kinopoisk.domain.usecases.RefreshCountryGenreSettingsUseCase
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
    private val application: Application,
    getPremieresUseCase: GetPremieresUseCase,
    getPopularUseCase: GetPopularUseCase,
    getTopListUseCase: GetTopListUseCase,
    getSeriesUseCase: GetSeriesUseCase,
    getDynamicGenreCountryUseCase: GetDynamicGenreCountryUseCase,
    private val refreshCountyAndGenreSettingsUseCase: RefreshCountryGenreSettingsUseCase
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = combine(
            getPremieresUseCase(),
            getPopularUseCase(),
            getTopListUseCase(),
            getSeriesUseCase(),
            getDynamicGenreCountryUseCase()
        ) { premieres, popular, top250, series, dynamics ->
            listOf(
                MovieSection(
                    application.getString(R.string.section_premieres_title),
                    premieres,
                    SectionType.PREMIERES
                ),
                MovieSection(
                    application.getString(R.string.section_popular_title),
                    popular,
                    SectionType.POPULAR
                ),
                MovieSection(
                    application.getString(R.string.section_top250_title),
                    top250,
                    SectionType.TOP_250
                ),
                MovieSection(
                    application.getString(R.string.section_series_title),
                    series,
                    SectionType.SERIES
                ),
                MovieSection(
                    application.getString(R.string.section_dynamic_title),
                    dynamics,
                    SectionType.DYNAMIC_GENRE_COUNTRY
                ).apply { title = getName() }
            )
        }
        .map<List<MovieSection>, HomeUiState> { sections -> HomeUiState.Success(sections) }
        .catch<HomeUiState> { exception -> emit(HomeUiState.Error(exception)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState.Loading
        )
    fun refreshCountryGenre() {
        refreshCountyAndGenreSettingsUseCase()
    }
}