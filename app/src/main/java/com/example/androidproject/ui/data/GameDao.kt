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

    @Query("SELECT * FROM game WHERE user_id = :user_id AND name = :name")
    fun getFavGameByUser(user_id: String, name: String): Game

    @Query("DELETE FROM game WHERE user_id = :user_id")
    fun deleteGameByUser(user_id: String)

    @Insert
    fun insertGame(game: Game)

    @Update
    fun updateGame(game: Game)

    @Delete
    fun deleteGame(game: Game)
}