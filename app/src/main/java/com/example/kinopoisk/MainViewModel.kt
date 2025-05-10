package com.example.kinopoisk

import androidx.lifecycle.ViewModel
import com.example.kinopoisk.data.Repository
import com.example.kinopoisk.data.model.Movie
import com.example.kinopoisk.domain.usecases.GetTopListUseCaseImpl

class MainViewModel(private val repository: Repository): ViewModel() {
    private val getTopListUseCase = GetTopListUseCaseImpl(repository)

    suspend fun topList(): List<Movie> {
        return getTopListUseCase.getTopList()
    }
}