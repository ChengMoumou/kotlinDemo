package com.example.kotlinpractice1

import android.app.Activity

object ActivityCollector {
    private val arrayList = ArrayList<Activity>()
    fun addAcvitiy(activity: Activity){
        arrayList.add(activity)
    }

    fun removeActivity(activity: Activity){
        arrayList.remove(activity)
    }

    fun clearAllActivity(){
        for (activity in arrayList){
            if (!activity.isFinishing)
                activity.finish()
        }
        arrayList.clear()
    }
}