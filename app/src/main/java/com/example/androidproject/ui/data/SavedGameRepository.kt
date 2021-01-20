package com.example.androidproject.ui.data

import android.content.Context
import com.example.androidproject.ui.home.GameInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class SavedGameRepository {

    companion object {
        var database: SavedGameDatabase? = null

        fun getSavedGames(context: Context): List<GameInfo>? {
            database = SavedGameDatabase.getDatabase(context)

            return database!!.getSavedGameDao().getAll()
        }

        fun insertSavedGame(context: Context, gameInfo: GameInfo) {
            database = SavedGameDatabase.getDatabase(context)

            CoroutineScope(IO).launch {
                database!!.getSavedGameDao().insertGame(gameInfo)
            }
        }

        fun updateSavedGame(context: Context, gameInfo: GameInfo) {
            database = SavedGameDatabase.getDatabase(context)

            CoroutineScope(IO).launch {
                database!!.getSavedGameDao().updateSavedGame(gameInfo)
            }
        }

        fun deleteSavedGame(context: Context, gameInfo: GameInfo) {
            database = SavedGameDatabase.getDatabase(context)
            database!!.getSavedGameDao().deleteSavedGame(gameInfo)
        }
    }
}