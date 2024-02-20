package com.example.weather

import android.app.Application
import android.content.Context
import android.media.session.MediaSession.Token

class SunnyWeatherApplication : Application() {
    companion object{
        const val Token = "BNBRvBveaD2VfHVI"
        lateinit var context:Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}