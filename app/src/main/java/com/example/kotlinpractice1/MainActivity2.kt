package com.example.kotlinpractice1

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main2.button
import kotlinx.android.synthetic.main.activity_main2.button1
import kotlinx.android.synthetic.main.activity_main2.text
import java.util.concurrent.TimeUnit
import kotlin.math.log

class MainViewModelFactory(private val countRes: Int) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(countRes) as T
    }
}

class MainActivity2 : AppCompatActivity() {
    private val TAG = "MainActivity2"
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val countRes = text.text.count()
        Log.d(TAG, "111111onCreate: ")
        lifecycle.addObserver(MyObserver(lifecycle))
        viewModel = ViewModelProvider(this, MainViewModelFactory(countRes)).get(MainViewModel::class.java)
        val request = PeriodicWorkRequest.Builder(SimpleWorkwe::class.java, 15 , TimeUnit.MINUTES).build()
        WorkManager.getInstance(this).enqueue(request)
        button.setOnClickListener {
            viewModel.addOne()
        }
        button1.setOnClickListener {
            viewModel.clear()
        }
        viewModel.counter.observe(this, Observer {
            sss -> Log.d(TAG, "onCreate: "+sss)
                    text.text = sss.toString()

        })
        val currentNightMode = this.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                Log.d(TAG, "onCreate: 浅色模式")
            } // Night mode is not active, we're using the light theme.
            Configuration.UI_MODE_NIGHT_YES -> {
                Log.d(TAG, "onCreate: 深色模式")
            } // Night mode is active, we're using dark theme.
        }
    }
    private fun refreshCounter(){
//            text.text = viewModel.toString()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "111111onStart: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "111111onStop: ")
    }
}