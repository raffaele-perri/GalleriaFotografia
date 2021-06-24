package com.example.app_domain.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_data.implementation.BeerRepositoryImpl
import com.example.app_domain.model.Beer
import com.example.galleria.framework.NetworkDataSourceImpl
import com.example.galleria.services.APIClient
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val beers: MutableLiveData<List<Beer>> = MutableLiveData<List<Beer>>()
    fun getBeers(): LiveData<List<Beer>>{
        return beers;
    }

    fun loadBeers(){
        loadBeers(1)
    }
    fun loadBeers(page: Int){
        //val call = APIClient.client.getBeerList(page)
        viewModelScope.launch {
            beers.postValue(BeerRepositoryImpl(NetworkDataSourceImpl()).getBeerList(page))
        }

//        call.enqueue(object : retrofit2.Callback<List<Beer>> {
//            override fun onResponse(call: Call<List<Beer>>?, response: Response<List<Beer>>?) {
//                Log.d("RESPONSE", "onResponse: ${response.toString()}")
//                if (response.isSuccessful) {
//                    Log.d("RESPONSE", "onResponse: ${response.body()!!}")
//                    beers.postValue(response?.body())
//                }
//            }
//            override fun onFailure(call: Call<List<Beer>>?, t: Throwable) {
//                Log.e("TAG", "onFailure: ERRATOOOOO ${t.localizedMessage}", )
//            }
//        })
    }

}


