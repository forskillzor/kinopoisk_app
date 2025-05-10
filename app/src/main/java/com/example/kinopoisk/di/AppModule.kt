package com.example.kinopoisk.di

import com.example.kinopoisk.data.Repository
import com.example.kinopoisk.data.api.KinopoiskApi
import com.example.kinopoisk.data.api.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideKinopoiskApi(): KinopoiskApi {
        return RetrofitClient.api
    }

    @Provides
    @Singleton
    fun provideRepository(api: KinopoiskApi): Repository {
        return Repository(api)
    }
}