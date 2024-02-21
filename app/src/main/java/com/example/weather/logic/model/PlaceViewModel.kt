package com.example.weather.logic.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weather.Repository
import com.example.weather.logic.network.Place
import com.example.weather.logic.network.PlaceResponse

class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    val placeList = ArrayList<Place>()
    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    // 没有开启线程 不需要借助 LiveData 对象来观察a
    fun savePlace(place: Place) = Repository.savePlace(place)

    fun getSavedPlace() = Repository.getSavedPlace()

    fun isPlaceSaved() = Repository.isPlaceSaved()
}