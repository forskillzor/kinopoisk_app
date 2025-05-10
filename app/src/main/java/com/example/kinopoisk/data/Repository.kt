package com.example.kinopoisk.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kinopoisk.data.api.RetrofitClient.api
import com.example.kinopoisk.data.api.TopListPagingSource
import com.example.kinopoisk.data.model.Movie
import kotlinx.coroutines.flow.Flow

class Repository private constructor() {
    fun topList(): Flow<PagingData<Movie>> {
        return Pager(config = PagingConfig(pageSize = 20)) {
            TopListPagingSource(api)
        }.flow
    }

    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        fun newInstance(): Repository{
            return INSTANCE?: synchronized(this) {
                INSTANCE ?: Repository().also { INSTANCE = it }
            }
        }
    }
}