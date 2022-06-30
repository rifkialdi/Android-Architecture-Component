package com.example.androidarchitecturecomponent_dicoding.live_data_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidarchitecturecomponent_dicoding.databinding.ActivityMainApiBinding
import com.example.androidarchitecturecomponent_dicoding.live_data_api.api.ApiConfig
import com.example.androidarchitecturecomponent_dicoding.live_data_api.api.Response
import retrofit2.*

class MainApiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainApiBinding
    private lateinit var mainApiViewModel: MainApiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.idrv.layoutManager = LinearLayoutManager(this)

        /* Inisialisasi ViewModel */
        mainApiViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainApiViewModel::class.java)
        /* lakukan observe data dari ViewModel */
        mainApiViewModel.notes.observe(this) { response ->
            findNotes(response)
        }
    }

    fun findNotes(response: Response) {
        val notes = response.notes
        val data = arrayListOf<String>()
        Log.e("data response json", "data : $notes", )

        for (item in notes) {
            data.add(item.note)
        }
        binding.idrv.adapter = ApiAdapter(data)
    }


}