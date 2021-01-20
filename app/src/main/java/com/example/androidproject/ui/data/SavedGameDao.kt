package com.example.androidproject.ui.data

import androidx.room.*
import com.example.androidproject.ui.home.GameInfo

@Dao
interface SavedGameDao {

    @Query("SELECT * FROM savedgame")
    fun getAll(): List<GameInfo>

    @Insert
    fun insertGame(gameInfo: GameInfo)

    @Update
    fun updateSavedGame(gameInfo: GameInfo)

    @Delete
    fun deleteSavedGame(gameInfo: GameInfo)
}