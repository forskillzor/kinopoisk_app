package com.example.kinopoisk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kinopoisk.data.model.MovieDto
import com.example.kinopoisk.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {
    val pagingData: Flow<PagingData<MovieDto>> = this.movieRepository.getTopPaged().cachedIn(viewModelScope)
}