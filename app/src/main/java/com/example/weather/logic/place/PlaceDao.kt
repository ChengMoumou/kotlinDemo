package com.example.weather.logic.place

import android.content.Context
import com.example.weather.SunnyWeatherApplication
import com.example.weather.logic.network.Place
import com.google.gson.Gson

object PlaceDao {
    private fun sharedPreferences() = SunnyWeatherApplication.context.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)

    fun savePlace(place: Place) {
        sharedPreferences().edit().apply() {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace() : Place{
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")
}