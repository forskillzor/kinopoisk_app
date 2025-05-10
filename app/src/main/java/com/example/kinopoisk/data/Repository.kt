package com.example.kinopoisk.data

import com.example.kinopoisk.data.api.RetrofitClient.api
import com.example.kinopoisk.data.model.Movie

class Repository private constructor() {
    suspend fun topList(): List<Movie> {
        return api.topList(page = 1).films
    }

    companion object {
        private var INSTANCE: Repository? = null
        fun newInstance(): Repository{
            if (INSTANCE == null){
                INSTANCE = Repository()
            }
            return INSTANCE!!
        }
    }
}