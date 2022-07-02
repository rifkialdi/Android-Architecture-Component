package com.example.androidarchitecturecomponent_dicoding.view_model

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    /* Membuat variabel untuk menampung data */
    var result = 0

    fun calculate(panjang: String, lebar: String) {
        /* Mengubah nilai ke variabel */
        result = panjang.toInt() * lebar.toInt()
    }
}