package com.example.androidproject.ui.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidproject.ui.home.GameInfo


@Database(
    entities = [GameInfo::class],
    version = 1
)
abstract class SavedGameDatabase : RoomDatabase() {
    abstract fun getSavedGameDao(): SavedGameDao

    companion object {
        @Volatile
        private var INSTANCE: SavedGameDatabase? = null

        fun getDatabase(context: Context): SavedGameDatabase {
            if (INSTANCE != null) {
                return INSTANCE!!
            }

            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, SavedGameDatabase::class.java, "games_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

                return INSTANCE!!
            }
        }
    }
}