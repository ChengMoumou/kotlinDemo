package com.example.kotlinpractice1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog

open class BaseActivity : AppCompatActivity() {
    val TAG = "BaseActivity"
    lateinit var broadcastReceiver : ForceBroadCastRecevier
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addAcvitiy(this)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: 接收广播的onresume")
        val  intentFilter = IntentFilter()
        broadcastReceiver =  ForceBroadCastRecevier()
        intentFilter.addAction("com.homurax.broadcastbestpractice.FORCE_OFFLINE")
        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: 接收广播")
        unregisterReceiver(broadcastReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: 接收广播")
        ActivityCollector.removeActivity(this)
    }

    inner class ForceBroadCastRecevier : BroadcastReceiver(){
        // 接收广播
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d(TAG, "onReceive: 接收广播=")
            context?.let {
                AlertDialog.Builder(it).apply {
                    setTitle("Warning")
                    setMessage("You are forced to be offline. Please try to login again.")
                    setCancelable(false)
                    setPositiveButton("OK") { _, _ ->
                        Log.d(TAG, "onReceive: 接收到广播啦")
                    }
                    show()
                }
            }
        }
    }
}