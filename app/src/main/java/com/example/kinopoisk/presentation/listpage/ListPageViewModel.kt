package com.example.kinopoisk.presentation.listpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.kinopoisk.data.model.SectionType
import com.example.kinopoisk.domain.entities.Movie
import com.example.kinopoisk.domain.usecases.GetDynamicGenreCountryPagedUseCaseImpl
import com.example.kinopoisk.domain.usecases.GetPopularPagedUseCaseImpl
import com.example.kinopoisk.domain.usecases.GetPremieresPagedUseCaseImpl
import com.example.kinopoisk.domain.usecases.GetSeriesPagedUseCaseImpl
import com.example.kinopoisk.domain.usecases.GetTopListPagedUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ListPageViewModel @Inject constructor(
    val getPremieresPagedUseCase: GetPremieresPagedUseCaseImpl,
    val getPopularPagedUseCase: GetPopularPagedUseCaseImpl,
    val getTopListPagedUseCase: GetTopListPagedUseCaseImpl,
    val getSeriesPagedUseCase: GetSeriesPagedUseCaseImpl,
    val getDynamicGenreCountryPagedUseCase: GetDynamicGenreCountryPagedUseCaseImpl

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