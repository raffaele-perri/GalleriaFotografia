package com.example.galleria.framework

import com.example.app_domain.model.Beer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {
    @GET("beers")
    suspend fun getBeerList(): Response<List<Beer>>;

    @GET("beers")
    suspend fun getBeerList(@Query("page") page : Int): Response<List<Beer>>;

    @GET("beers/{id}")
    suspend fun getBeerById(@Path("id") id : Long): Response<List<Beer>>;

}