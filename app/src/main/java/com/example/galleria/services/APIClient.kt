package com.example.galleria.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    companion object {
        private const val BASEURL = "https://api.punkapi.com/v2/"

        fun create() : APIInterface {
            val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit.create(APIInterface::class.java)
        }
    }
}