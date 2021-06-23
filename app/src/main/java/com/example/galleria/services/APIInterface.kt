package com.example.galleria.services

import com.example.galleria.model.Beer
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("beers")
    fun getBeerList(): Call<List<Beer>>;
}