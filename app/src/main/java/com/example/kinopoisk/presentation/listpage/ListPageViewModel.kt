package com.example.kinopoisk.presentation.listpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.kinopoisk.data.model.SectionType
import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.usecases.GetDynamicGenreCountryPagedUseCase
import com.example.kinopoisk.domain.usecases.GetPopularPagedUseCase
import com.example.kinopoisk.domain.usecases.GetPremieresPagedUseCase
import com.example.kinopoisk.domain.usecases.GetSeriesPagedUseCase
import com.example.kinopoisk.domain.usecases.GetTopListPagedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

@HiltViewModel
class ListPageViewModel @Inject constructor(
    val getPremieresPagedUseCase: GetPremieresPagedUseCase,
    val getPopularPagedUseCase: GetPopularPagedUseCase,
    val getTopListPagedUseCase: GetTopListPagedUseCase,
    val getSeriesPagedUseCase: GetSeriesPagedUseCase,
    val getDynamicGenreCountryPagedUseCase: GetDynamicGenreCountryPagedUseCase

): ViewModel() {
    private val _pagingData = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val pagingData = _pagingData.asStateFlow()

    fun loadMovies(sectionType: SectionType) {
        viewModelScope.launch {
            when(sectionType) {
                SectionType.PREMIERES -> getPremieresPagedUseCase()
                SectionType.POPULAR -> getPopularPagedUseCase()
                SectionType.TOP_250 -> getTopListPagedUseCase()
                SectionType.SERIES -> getSeriesPagedUseCase()
                SectionType.DYNAMIC_GENRE_COUNTRY -> getDynamicGenreCountryPagedUseCase()
            }.collect { pagingData ->
                _pagingData.value = pagingData
            }
        }
    }
}