package com.example.app_domain.repository

import com.example.app_domain.model.Beer

interface IBeerRepository {

    suspend fun getBeerList(): List<Beer>

    suspend fun getBeerList(page : Int): List<Beer>

    suspend fun getBeerById(id : Long): Beer


    suspend fun getBeers(): List<Beer>

    suspend fun insertBeers(beers: List<Beer>)

    suspend fun removeBeers(beers: List<Beer>)

    suspend fun getFavouriteBeerById(beerId: Long) : List<Beer>

    suspend fun isFavouriteBeer(beerId : Long) : Boolean
}