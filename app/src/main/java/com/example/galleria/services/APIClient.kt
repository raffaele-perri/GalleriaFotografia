package com.example.galleria.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    //companion object {
    private const val BASEURL = "https://api.punkapi.com/v2/"
    private val retrofit: Retrofit by lazy{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        Retrofit.Builder().baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
    }
    val client : APIInterface by lazy{
        retrofit.create(APIInterface::class.java)
    }
  //  }
}
