package com.example.galleria.framework

import android.util.Log
import com.example.app_data.networking.INetworkDataSource
import com.example.app_domain.model.Beer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkDataSourceImpl @Inject constructor(private val apiService: APIInterface): INetworkDataSource {


    override suspend fun getBeerList(page: Int): List<Beer> {
        return try {
            val response = apiService.getBeerList(page)
            if (response.isSuccessful) {
                Log.d("RESPONSE", "onResponse: ${response.body()!!}")
                response.body()
            } else
                emptyList<Beer>()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList<Beer>()
        }!!
    }

    override suspend fun getBeerById(id: Long): Beer {
        return try {
            val response = apiService.getBeerById(id)
            if (response.isSuccessful) {
                Log.d("RESPONSE", "onResponse: ${response.body()!!}")
                response.body()?.get(0)
            } else
                Beer(0, "Nessuna", "Vuoto", "Niente", "")
        } catch (e: Exception) {
            e.printStackTrace()
            Beer(0, "Nessuna", "Vuoto", "Niente", "")
        }!!
    }

}