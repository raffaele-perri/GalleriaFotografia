package com.example.galleria.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galleria.model.Beer
import com.example.galleria.services.APIClient
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val beer : MutableLiveData<Beer> = MutableLiveData<Beer>()

    fun getBeerDetail(): LiveData<Beer>{
        return beer
    }
    fun loadBeerDetail(id:Long) {
        viewModelScope.launch {
            try {
                val response = APIClient.client.getBeerById(id)
                if (response.isSuccessful) {
                    Log.d("RESPONSE", "onResponse: ${response.body()!!}")
                    val beers: List<Beer>? = response.body()
                    Log.d("RESPONSE", "loadBeerDetail: ${beers!![0]}")
                    beer.postValue((beers!![0]))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}