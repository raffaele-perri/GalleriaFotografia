package com.example.galleria.framework.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BeerDAO {

    @Query("SELECT * FROM Beer")
    suspend fun getAllBeers(): List<BeerDatabaseModel>

    @Insert
    suspend fun insertAll(beers: List<BeerDatabaseModel>)

    @Delete
    suspend fun delete(beer: BeerDatabaseModel)
}