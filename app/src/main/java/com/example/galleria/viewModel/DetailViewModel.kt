package com.example.galleria.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.galleria.model.Beer

class DetailViewModel : ViewModel() {

    private val beer : MutableLiveData<Beer> = MutableLiveData<Beer>()

    fun getBeerDetail(): LiveData<Beer>{
        return beer
    }
    fun loadBeerDetail() {
        TODO("Not yet implemented")
    }
}