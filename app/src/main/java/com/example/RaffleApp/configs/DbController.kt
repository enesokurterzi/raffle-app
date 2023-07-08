package com.example.RaffleApp.configs

import android.app.Activity
import android.content.Context
import androidx.room.Room

class DbController(val context: Context): Activity() {
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "appDataBase"
    )
        .allowMainThreadQueries()
        .build()
}