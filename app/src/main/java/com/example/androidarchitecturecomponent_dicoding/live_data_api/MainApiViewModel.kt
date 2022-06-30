package com.example.androidarchitecturecomponent_dicoding.live_data_api

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidarchitecturecomponent_dicoding.live_data_api.api.ApiConfig
import com.example.androidarchitecturecomponent_dicoding.live_data_api.api.Response
import retrofit2.Call
import retrofit2.Callback

class MainApiViewModel : ViewModel() {

    private val _notes = MutableLiveData<Response>()
    val notes: LiveData<Response> = _notes /* objek LiveData nilai nya dari objek MutableLiveData*/

    init {
        findNote()
    }

    fun findNote() {
        val retrofit = ApiConfig.getApiService().getData()
        retrofit.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful) {
                    /* objek MutableLiveData mendapatkan data */
                    _notes.value = response.body()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "data : ${t.message}", )
            }
        })
    }
}