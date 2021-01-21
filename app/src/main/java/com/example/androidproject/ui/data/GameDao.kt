package com.example.androidproject.ui.data

import androidx.room.*

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun getAll(): List<Game>

    @Insert
    fun insertGame(game: Game)

    @Update
    fun updateGame(game: Game)

    @Delete
    fun deleteGame(game: Game)
}