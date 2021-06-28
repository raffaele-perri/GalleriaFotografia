package com.example.app_data.database

import com.example.app_domain.model.Beer

interface IDatabaseDataSource {

    suspend fun insertBeers(beers: List<Beer>)

    suspend fun getBeers(): List<Beer>

    suspend fun deleteBeer(beer : Beer)
}