package com.example.galleria.framework

import android.util.Log
import com.example.app_data.networking.INetworkDataSource
import com.example.app_domain.model.Beer
import com.example.galleria.services.APIClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkDataSourceImpl : INetworkDataSource {


    override suspend fun getBeerList(page: Int): List<Beer> {
        return try {
            val response = APIClient.client.getBeerList(page)
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
        return (try {
            val response = APIClient.client.getBeerById(id)
            if (response.isSuccessful) {
                Log.d("RESPONSE", "onResponse: ${response.body()!!}")
                response.body()?.get(0)
            } else
                Beer(0, "Nessuna", "Vuoto", "Niente", "")
        } catch (e: Exception) {
            e.printStackTrace()
            Beer(0, "Nessuna", "Vuoto", "Niente", "")
        } as Beer?)!!
    }

}