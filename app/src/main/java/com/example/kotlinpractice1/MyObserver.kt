package com.example.kotlinpractice1

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyObserver(val lifecycle: Lifecycle): LifecycleObserver {
    private val TAG = "111111MyObserver"

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun activityStar(){
        Log.d(TAG, "activityStar: ")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop(){
        Log.d(TAG, "activityStop: ")
    }

    fun getLife(){
        Log.d(TAG, "getLife: ===+"+lifecycle.currentState)
    }
}