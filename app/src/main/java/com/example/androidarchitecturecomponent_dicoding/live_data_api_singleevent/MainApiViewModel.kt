package com.example.androidarchitecturecomponent_dicoding.live_data_api_singleevent

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidarchitecturecomponent_dicoding.live_data_api_singleevent.api.ApiConfig
import com.example.androidarchitecturecomponent_dicoding.live_data_api_singleevent.api.Response
import com.example.androidarchitecturecomponent_dicoding.live_data_api_singleevent.singleevent.Event
import retrofit2.Call
import retrofit2.Callback

class MainApiViewModel : ViewModel() {

    /* variabel untuk menampung data */
    private val _notes = MutableLiveData<Response>()
    val notes: LiveData<Response> = _notes /* objek LiveData nilai nya dari objek MutableLiveData*/

    /* menggunakan single event (wrapper) */
    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    /* ketika class diinisialisasi ini di blok ini langsung berjalan */
    init {
        findNote()
    }

    fun findNote() {
        val retrofit = ApiConfig.getApiService().getData()

        retrofit.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful) {
                    /* objek MutableLiveData mendapatkan data */
                    Log.e("ERROR FINDNOTE()", "${response.body()}", )
                    _notes.value = response.body()
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "data : ${t.message}", )
            }
        })
    }

    fun postNote(note: String) {
        val retrofit = ApiConfig.getApiService().postData(note)

        retrofit.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful) {
                    findNote()
                    _snackbarText.value = Event("String")
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("TAG", "${t.message}", )
            }
        })
    }
}