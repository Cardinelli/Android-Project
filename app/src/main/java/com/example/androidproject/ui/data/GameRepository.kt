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

        fun getGameByName(context: Context, name: String): Game {
            database = GameDatabase.getDatabase(context)

            return database!!.getGameDao().getGameByName(name)
        }

        fun getGameByUser(context: Context, user_id: String): List<Game> {
            database = GameDatabase.getDatabase(context)

            return database!!.getGameDao().getGameByUser(user_id)
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