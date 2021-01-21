package com.example.androidproject.ui.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidproject.ui.home.GameInfo


@Database(
    entities = [Game::class],
    version = 1
)
abstract class GameDatabase : RoomDatabase() {
    abstract fun getGameDao(): GameDao

    companion object {
        @Volatile
        private var INSTANCE: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase {
            if (INSTANCE != null) {
                return INSTANCE!!
            }

            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, GameDatabase::class.java, "game_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

                return INSTANCE!!
            }
        }
    }
}