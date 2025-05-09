package com.example.kinopoisk.data.api

import com.example.kinopoisk.BuildConfig
import com.example.kinopoisk.data.model.Movie
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface KinopoiskApi {
    @GET("/api/v2.2/films/top?type=TOP_250_BEST_FILMS")
    suspend fun topList(@Query("page") page: Int): List<Movie>

    companion object {
        const val BASE_URL = "https://kinopoiskapiunofficial.tech"
    }
}

object RetrofitClient {
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-API-KEY", BuildConfig.API_KEY)
                .build()
            chain.proceed(request)
        }
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(KinopoiskApi.BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val api: KinopoiskApi = retrofit.create(KinopoiskApi::class.java)
}
