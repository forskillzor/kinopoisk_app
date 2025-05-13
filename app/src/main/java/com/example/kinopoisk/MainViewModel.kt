package com.example.kinopoisk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kinopoisk.data.repository.Repository
import com.example.kinopoisk.data.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    val pagingData: Flow<PagingData<Movie>> = this.repository.getTopPaged().cachedIn(viewModelScope)
}