package com.example.kinopoisk.di

import android.content.Context
import androidx.room.Room
import com.example.kinopoisk.data.local.AppDatabase
import com.example.kinopoisk.data.local.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database.db"
        ).build()
    }

    @Provides fun provideMovieDao(db: AppDatabase): MovieDao = db.movieDao()
}
