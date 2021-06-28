package com.example.galleria.framework.database

import com.example.app_data.database.IDatabaseDataSource
import com.example.app_domain.model.Beer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseDataSourceImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : IDatabaseDataSource {

    override suspend fun insertBeers(beers: List<Beer>) {
        appDatabase.beerDAO().insertBeers(beers.toDatabaseModel())
    }

    override suspend fun getBeers(): List<Beer> {
        return appDatabase.beerDAO().getAllBeers().toDomainModel()
    }

    override suspend fun removeBeers(beers: List<Beer>) {
        appDatabase.beerDAO().removeBeers(beers.toDatabaseModel())
    }

    override suspend fun getBeerById(beerId : Long): List<Beer> {
        return appDatabase.beerDAO().getBeerById(beerId).toDomainModel()
    }
}