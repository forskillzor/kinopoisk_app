package com.example.kinopoisk.di

import com.example.kinopoisk.data.repository.NetworkRepository
import com.example.kinopoisk.data.repository.Repository
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
    fun provideRepository(networkRepository: NetworkRepository): Repository {
        return networkRepository
    }
}