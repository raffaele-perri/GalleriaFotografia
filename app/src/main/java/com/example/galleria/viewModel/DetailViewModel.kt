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
class DetailViewModel @Inject constructor(private val beerRepository: IBeerRepository): ViewModel() {

    private val beer : MutableLiveData<Beer> = MutableLiveData<Beer>()
    private var fav : MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun isFavourite() : LiveData<Boolean>{
        return fav
    }

    fun getBeerDetail(): LiveData<Beer>{
        return beer
    }
    fun loadBeerDetail(id:Long) {
        viewModelScope.launch {
            beer.postValue(beerRepository.getBeerById(id))
        }
    }

    fun insertBeer(beer:Beer){
        viewModelScope.launch {
            beerRepository.insertBeers(listOf(beer))
        }
    }

    fun removeBeer(beer:Beer){
        viewModelScope.launch {
            beerRepository.removeBeers(listOf(beer))
        }
    }

    fun isBeerPresent(beer:Beer){
        viewModelScope.launch {
            fav.postValue(beerRepository.isFavouriteBeer(beer.id))
        }
    }
}