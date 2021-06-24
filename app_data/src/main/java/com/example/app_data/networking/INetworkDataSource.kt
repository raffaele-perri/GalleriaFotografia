package com.example.app_data.networking

import com.example.app_domain.model.Beer

interface INetworkDataSource {

    //suspend fun getBeerList(): List<Beer>

    suspend fun getBeerList(page: Int): List<Beer>

    suspend fun getBeerById(id: Long): Beer
}