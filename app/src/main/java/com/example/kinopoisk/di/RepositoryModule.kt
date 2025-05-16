package com.example.kinopoisk.di

import com.example.kinopoisk.data.repository.MovieRepositoryImpl
import com.example.kinopoisk.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository {
        return movieRepositoryImpl
    }
}