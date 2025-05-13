package com.example.kinopoisk.domain.usecases

import com.example.kinopoisk.data.repository.Repository
import com.example.kinopoisk.data.model.Movie

interface GetTopListUseCase {

    suspend fun getTopList(): List<Movie>
}

//class GetTopListUseCaseImpl(val repository: Repository): GetTopListUseCase {
//    override suspend fun getTopList(): List<Movie> {
//        return repository.topList()
//    }
//
//}