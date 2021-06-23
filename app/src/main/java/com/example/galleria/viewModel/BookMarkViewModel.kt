package com.example.galleria.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.galleria.model.Beer

class BookMarkViewModel : ViewModel() {

    private val favouriteBeers: MutableLiveData<List<Beer>> = MutableLiveData<List<Beer>>()

    fun getFavouriteBeers(): LiveData<List<Beer>> {
        return favouriteBeers;
    }
    fun loadFavouriteBeers() {
        TODO("Not yet implemented")
    }
}