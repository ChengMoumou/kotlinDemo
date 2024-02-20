package com.example.weather.logic.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weather.Repository
import com.example.weather.logic.network.PlaceResponse

class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    val placeList = ArrayList<PlaceResponse.Place>()
    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPla(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
}