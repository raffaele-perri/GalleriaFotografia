package com.example.galleria.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_domain.model.Beer
import com.example.app_domain.repository.IBeerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(private val beerRepository: IBeerRepository): ViewModel() {

    private val favouriteBeers: MutableLiveData<List<Beer>> = MutableLiveData<List<Beer>>()

    fun getFavouriteBeers(): LiveData<List<Beer>>{
        return favouriteBeers
    }

    fun insertBeers(beer:Beer){
        viewModelScope.launch {
            beerRepository.insertBeers(listOf(beer))
        }
    }

    fun loadFavouriteBeers() {
        viewModelScope.launch {
            favouriteBeers.postValue(beerRepository.getBeers())
        }
    }
}