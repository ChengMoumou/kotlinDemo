package com.example.kotlinpractice1

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService: Service() {
    val TAG = "MyService"
    val binder = DownloadBinder()
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
        return binder
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    class DownloadBinder:Binder(){
        private  val TAG = "MyService"
        fun startDownloan(){
            Log.d(TAG, "startDownloan: 开始下载")
        }
        fun onProgress(){
            Log.d(TAG, "onProgress: 下载中，，，，，，")
        }
    }
}