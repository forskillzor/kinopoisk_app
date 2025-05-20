package com.example.kinopoisk.di

import android.content.Context
import com.example.kinopoisk.data.local.DataStoreSettings
import com.example.kinopoisk.data.repository.MovieRepositoryImpl
import com.example.kinopoisk.domain.repository.MovieRepository
import com.example.kinopoisk.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideSettingsRepository(@ApplicationContext context: Context): SettingsRepository{
        return DataStoreSettings(context)
    }
}