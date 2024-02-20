package com.example.weather.logic.model

import com.homurax.sunnyweather.logic.model.DailyResponse
import com.homurax.sunnyweather.logic.model.RealtimeResponse

class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)