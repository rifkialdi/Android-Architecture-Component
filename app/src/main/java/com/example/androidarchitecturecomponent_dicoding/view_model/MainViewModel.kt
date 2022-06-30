package com.example.androidarchitecturecomponent_dicoding.view_model

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var result = 0

    fun calculate(panjang: String, lebar: String) {
        result = panjang.toInt() * lebar.toInt()
    }
}