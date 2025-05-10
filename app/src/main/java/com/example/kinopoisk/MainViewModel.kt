package com.example.kinopoisk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kinopoisk.data.Repository
import com.example.kinopoisk.data.model.Movie
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val repository: Repository): ViewModel() {
    val pagingData: Flow<PagingData<Movie>> = repository.topList().cachedIn(viewModelScope)
}