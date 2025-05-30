package com.example.kinopoisk.presentation.listpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kinopoisk.data.model.SectionType
import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.usecases.GetDynamicGenreCountryPagedUseCase
import com.example.kinopoisk.domain.usecases.GetDynamicGenreCountryPagedUseCaseImpl
import com.example.kinopoisk.domain.usecases.GetPopularPagedUseCase
import com.example.kinopoisk.domain.usecases.GetPopularPagedUseCaseImpl
import com.example.kinopoisk.domain.usecases.GetPremieresPagedUseCase
import com.example.kinopoisk.domain.usecases.GetPremieresPagedUseCaseImpl
import com.example.kinopoisk.domain.usecases.GetSeriesPagedUseCase
import com.example.kinopoisk.domain.usecases.GetSeriesPagedUseCaseImpl
import com.example.kinopoisk.domain.usecases.GetTopListPagedUseCase
import com.example.kinopoisk.domain.usecases.GetTopListPagedUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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
                SectionType.PREMIERES -> getPremieresPagedUseCase().cachedIn(viewModelScope)
                SectionType.POPULAR -> getPopularPagedUseCase().cachedIn(viewModelScope)
                SectionType.TOP_250 -> getTopListPagedUseCase().cachedIn(viewModelScope)
                SectionType.SERIES -> getSeriesPagedUseCase().cachedIn(viewModelScope)
                SectionType.DYNAMIC_GENRE_COUNTRY -> getDynamicGenreCountryPagedUseCase().cachedIn(viewModelScope)
            }.collect { pagingData ->
                _pagingData.value = pagingData
            }
        }
    }
}