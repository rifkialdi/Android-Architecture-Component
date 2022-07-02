package com.example.androidarchitecturecomponent_dicoding.live_data_api

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidarchitecturecomponent_dicoding.databinding.ActivityMainApiBinding
import com.example.androidarchitecturecomponent_dicoding.live_data_api.api.Response

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

        binding.idbtnTambahdata.setOnClickListener {
            val note = binding.inputan.text.toString()
            if (note.isNotEmpty()) {
                mainApiViewModel.postNote(note)

                /* Agar keyboar otomatis hilang */
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(it.windowToken, 0)
            }

        }
    }

    /* Mengambil data json response dan di proses untuk class adapter */
    fun findNotes(response: Response) {
        val notes = response.notes
        val data = arrayListOf<String>()

        for (item in notes) {
            data.add(item.note)
        }
        binding.idrv.adapter = ApiAdapter(data)
    }


}