package com.example.app_data.implementation

import com.example.app_data.database.IDatabaseDataSource
import com.example.app_data.networking.INetworkDataSource
import com.example.app_domain.model.Beer
import com.example.app_domain.repository.IBeerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeerRepositoryImpl @Inject constructor(
    private val networkDataSource: INetworkDataSource,
    private val databaseDataSource: IDatabaseDataSource
) : IBeerRepository {
    override suspend fun getBeerList(): List<Beer> {
        return getBeerList(1)
    }

    override suspend fun getBeerList(page: Int): List<Beer>{
        return networkDataSource.getBeerList(page)
    }

    override suspend fun getBeerById(id: Long): Beer {
        return networkDataSource.getBeerById(id)
    }

    override suspend fun getBeers(): List<Beer>{
        return databaseDataSource.getBeers()
    }

    override suspend fun insertBeers(beers: List<Beer>){
        databaseDataSource.insertBeers(beers)
    }

    override suspend fun removeBeers(beers: List<Beer>){
        databaseDataSource.removeBeers(beers)
    }

    override suspend fun getFavouriteBeerById(beerId : Long) : List<Beer> {
        return databaseDataSource.getBeerById(beerId)
    }

    override suspend fun isFavouriteBeer(beerId : Long) : Boolean {
        return databaseDataSource.getBeerById(beerId).isNotEmpty()
    }

}