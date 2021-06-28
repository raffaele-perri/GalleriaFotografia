package com.example.galleria.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BeerDatabaseModel::class],
    version = 1
)
abstract class AppDatabase :RoomDatabase() {

    abstract fun beerDAO() : BeerDAO
}