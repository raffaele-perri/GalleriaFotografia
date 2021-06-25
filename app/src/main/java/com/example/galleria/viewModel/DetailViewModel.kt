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

    fun getBeerDetail(): LiveData<Beer>{
        return beer
    }
    fun loadBeerDetail(id:Long) {
        viewModelScope.launch {
            beer.postValue(beerRepository.getBeerById(id))
        }

    }
}