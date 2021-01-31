package com.example.androidproject.ui.data

import androidx.room.*

@Dao
interface GameDao {

    @Query("SELECT * FROM game")
    fun getAll(): List<Game>

    @Query("SELECT * FROM game WHERE name LIKE :name")
    fun getGameByName(name: String): Game

    @Query("SELECT * FROM game WHERE user_id = :user_id")
    fun getGameByUser(user_id: String): List<Game>

    @Insert
    fun insertGame(game: Game)

    @Update
    fun updateGame(game: Game)

    @Delete
    fun deleteGame(game: Game)
}