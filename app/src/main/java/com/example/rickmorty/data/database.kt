package com.example.rickmorty.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickmorty.models.Info

@Database(entities = [Info::class], version = 1)
class database {
    abstract class database : RoomDatabase()
}
