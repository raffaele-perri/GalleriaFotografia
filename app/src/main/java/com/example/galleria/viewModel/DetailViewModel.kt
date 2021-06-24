package com.example.galleria.viewModel

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

class DetailViewModel : ViewModel() {

    private val beer : MutableLiveData<Beer> = MutableLiveData<Beer>()

    fun getBeerDetail(): LiveData<Beer>{
        return beer
    }
    fun loadBeerDetail(id:Long) {
        viewModelScope.launch {
            beer.postValue(BeerRepositoryImpl(NetworkDataSourceImpl()).getBeerById(id))
        }

    }
}