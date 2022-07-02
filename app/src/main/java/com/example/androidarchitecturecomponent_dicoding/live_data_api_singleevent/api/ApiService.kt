package com.example.androidarchitecturecomponent_dicoding.live_data_api_singleevent.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("data.php")
    fun getData(): Call<Response>

    @FormUrlEncoded
    @POST("create.php")
    fun postData(
        @Field("note") message: String
    ): Call<Response>
}