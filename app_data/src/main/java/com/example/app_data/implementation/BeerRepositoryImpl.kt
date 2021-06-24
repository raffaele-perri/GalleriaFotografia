package com.example.app_data.implementation

import com.example.app_data.networking.INetworkDataSource
import com.example.app_domain.model.Beer
import com.example.app_domain.repository.IBeerRepository

class BeerRepositoryImpl(private val dataSource: INetworkDataSource) : IBeerRepository {
    override suspend fun getBeerList(): List<Beer> {
        return getBeerList(1)
    }

    override suspend fun getBeerList(page: Int): List<Beer>{
        return dataSource.getBeerList(page)
    }

    override suspend fun getBeerById(id: Long): Beer {
        return dataSource.getBeerById(id)
    }
}