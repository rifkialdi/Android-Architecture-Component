package com.example.androidarchitecturecomponent_dicoding.live_data_api.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {

        fun getApiService(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.100.35/crud-restapi-lazday/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}