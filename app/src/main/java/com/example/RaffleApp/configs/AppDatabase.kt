package com.example.RaffleApp.configs

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.RaffleApp.dao.CekilisDao
import com.example.RaffleApp.models.Cekilis

@Database(entities = [Cekilis::class], version = 1)
abstract class AppDatabase: RoomDatabase(){

    abstract fun cekilisDao(): CekilisDao

}