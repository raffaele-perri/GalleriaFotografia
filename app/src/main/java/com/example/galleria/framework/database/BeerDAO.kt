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
    suspend fun insertBeers(beers: List<BeerDatabaseModel>)

    @Delete
    suspend fun removeBeers(beers: List<BeerDatabaseModel>)

    @Query("SELECT * FROM Beer WHERE id= :beerId")
    suspend fun getBeerById(beerId : Long) : List<BeerDatabaseModel>
}