package com.example.kinopoisk.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Update
    suspend fun update(movie: MovieEntity)

    @Query("SELECT * FROM  movies WHERE id = :id")
    suspend fun get(id:  Int): MovieEntity?

    @Query("SELECT * FROM movies WHERE isWatched = 1")
    suspend fun getWatchedMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE isInCollection = 1")
    suspend fun getInCollectionMovies():  Flow<List<MovieEntity>>
}