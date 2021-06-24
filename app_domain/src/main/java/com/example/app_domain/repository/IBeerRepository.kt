package com.example.app_domain.repository

import com.example.app_domain.model.Beer
import retrofit2.Response

interface IBeerRepository {

    suspend fun getBeerList(): List<Beer>;

    suspend fun getBeerList(page : Int): List<Beer>;

    suspend fun getBeerById(id : Long): Beer;
}