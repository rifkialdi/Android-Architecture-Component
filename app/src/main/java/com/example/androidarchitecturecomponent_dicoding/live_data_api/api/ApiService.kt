package com.example.androidarchitecturecomponent_dicoding.live_data_api.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("data.php")
    fun getData(): Call<Response>
}