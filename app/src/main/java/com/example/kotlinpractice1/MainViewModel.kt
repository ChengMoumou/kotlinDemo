package com.example.kotlinpractice1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(countReserved: Int) : ViewModel() {
    var counter = MutableLiveData<Int>()
    init {
        counter.value = countReserved
    }

    fun addOne(){
        counter.value = counter.value?.plus(1)
    }

    fun clear(){
        counter.value = 0
    }
}