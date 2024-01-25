package ru.samsung.smartintercom.data.callHistory

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CallInfoRoom::class], version = 1)
@TypeConverters(CallTimeConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): CallInfoDAO
}