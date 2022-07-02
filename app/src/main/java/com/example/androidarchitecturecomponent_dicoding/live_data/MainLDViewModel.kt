package com.example.androidarchitecturecomponent_dicoding.live_data

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class MainLDViewModel : ViewModel() {
//  Untuk keperluan timer
    companion object {
        private const val ONE_SECOND = 1000
    }
    private val mInitialTime = SystemClock.elapsedRealtime()

//  LiveData yang value nya bisa di ubah (MutableLiveData)
    private val mElapsedTime = MutableLiveData<Long?>()

    init {
//         Membuat timer dan di simpan di variabel mElapsedTime
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                mElapsedTime.postValue(newValue)
            }
        }, ONE_SECOND.toLong(), ONE_SECOND.toLong())
    }

    fun getElapsedTime(): LiveData<Long?> {
        return mElapsedTime
    }
}