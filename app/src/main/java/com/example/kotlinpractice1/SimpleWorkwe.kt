package com.example.kotlinpractice1

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class SimpleWorkwe(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        TODO("Not yet implemented")
        Log.d("SimpleWorker", "do work in SimpleWorker")
        return Result.success()
    }
}