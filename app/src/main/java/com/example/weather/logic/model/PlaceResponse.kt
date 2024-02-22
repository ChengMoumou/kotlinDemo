package com.example.weather.logic.network

import com.google.gson.annotations.SerializedName
import java.net.Inet4Address

class Location(val lng: String, val lat: String)
class Place(
    val name: String,
    val location: Location,
    @SerializedName("formatted_address") val address: String
)

class PlaceResponse(val status: String, val places: List<Place>)
