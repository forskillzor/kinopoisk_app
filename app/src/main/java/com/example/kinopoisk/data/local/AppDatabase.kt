package com.example.kinopoisk.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(MovieConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}