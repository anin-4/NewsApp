package com.example.newsapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities=[NewsAppRoomEntity::class],version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class NewsAppDatabase:RoomDatabase() {

        abstract fun NewsAppDao():NewsAppDao
}