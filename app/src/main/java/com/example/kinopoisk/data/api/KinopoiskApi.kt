package com.example.kinopoisk.data.api

import com.example.kinopoisk.BuildConfig
import com.example.kinopoisk.data.model.CollectionsResponse
import com.example.kinopoisk.data.model.Top250Response
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface KinopoiskApi {
    @GET("/api/v2.2/films/top?type=TOP_250_BEST_FILMS")
    suspend fun top250(@Query("page") page: Int): Top250Response

    @GET("/api/v2.2/films/collections")
    suspend fun premieres(@Query("type") type: String = "CLOSES_RELEASES"): CollectionsResponse

    @GET("/api/v2.2/films/collections")
    suspend fun popular(@Query("type") type: String = "TOP_POPULAR_ALL"): CollectionsResponse

    @GET("/api/v2.2/films/collections")
    suspend fun series(@Query("type") type: String = "POPULAR_SERIES"): CollectionsResponse

    @GET("/api/v2.2/films/film")
    suspend fun dynamicGenresAndCountries(
        @Query("countries") countries: List<Int> = listOf(1),
        @Query("genres") genres: List<Int> = listOf(1)
    ): Top250Response



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
                .addHeader("x-api-key", BuildConfig.API_KEY)
                .addHeader("Content-Type", "application/json")
                .build()
            chain.proceed(request)
        }
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(KinopoiskApi.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val api: KinopoiskApi = retrofit.create(KinopoiskApi::class.java)
}
