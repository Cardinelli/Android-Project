package com.example.androidproject.ui.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Game(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "released") var released: String,
    @ColumnInfo(name = "background_image") var background_image: String,
    @ColumnInfo(name = "rating") var rating: Float,
    @ColumnInfo(name = "user_id") var user_id: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)