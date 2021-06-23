package com.example.galleria.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.galleria.model.Beer
import com.example.galleria.services.APIClient
import retrofit2.Call
import retrofit2.Response

class ListViewModel : ViewModel() {

    private val beers: MutableLiveData<List<Beer>> = MutableLiveData<List<Beer>>()
    fun getBeers(): LiveData<List<Beer>>{
        return beers;
    }
    fun loadBeers(){
        val call = APIClient.create().getBeerList()
        call.enqueue(object : retrofit2.Callback<List<Beer>> {
            override fun onResponse(call: Call<List<Beer>>?, response: Response<List<Beer>>?) {
                Log.d("RESPONSE", "onResponse: ${response.toString()}")
                if (response?.body() != null) {
//                         recyclerView.apply {
//                         setHasFixedSize(true)
//                         layoutManager = LinearLayoutManager(this@MainActivity)
//                         adapter = MoviesAdapter(response.body()!!.results)
                    Log.d("RESPONSE", "onResponse: ${response.body()!!}")
                }
            }
            override fun onFailure(call: Call<List<Beer>>?, t: Throwable) {
                Log.e("TAG", "onFailure: ERRATOOOOO ${t.localizedMessage}", )
            }
        })
    }

}


