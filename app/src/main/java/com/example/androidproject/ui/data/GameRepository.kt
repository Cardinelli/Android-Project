package com.example.androidproject.ui.data

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class GameRepository {

    companion object {
        var database: GameDatabase? = null

        fun getGames(context: Context): List<Game> {
            database = GameDatabase.getDatabase(context)

            return database!!.getGameDao().getAll()
        }

        fun insertGame(context: Context, game: Game) {
            database = GameDatabase.getDatabase(context)

            CoroutineScope(IO).launch {
                database!!.getGameDao().insertGame(game)
            }
        }

        fun updateGame(context: Context, game: Game) {
            database = GameDatabase.getDatabase(context)

            CoroutineScope(IO).launch {
                database!!.getGameDao().updateGame(game)
            }
        }

        fun deleteGame(context: Context, game: Game) {
            database = GameDatabase.getDatabase(context)
            database!!.getGameDao().deleteGame(game)
        }
    }
}