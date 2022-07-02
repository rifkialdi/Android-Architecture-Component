package com.example.androidarchitecturecomponent_dicoding.live_data_api_singleevent

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidarchitecturecomponent_dicoding.databinding.ActivityMainApiBinding
import com.example.androidarchitecturecomponent_dicoding.live_data_api_singleevent.api.Response
import com.google.android.material.snackbar.Snackbar

class MainApiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainApiBinding
    private lateinit var mainApiViewModel: MainApiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.idrv.layoutManager = LinearLayoutManager(this)

//      Inisialisasi ViewModel
        mainApiViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainApiViewModel::class.java)

//      lakukan observe data pada variabel notes dari ViewModel
        mainApiViewModel.notes.observe(this) { response ->
            findNotes(response)
        }

//      observe pada variabel snackbarText, ini menggunak metode singleEvent
        mainApiViewModel.snackbarText.observe(this) {
            it.getContentIfNotHandled()?.let { snackBarText ->
                Snackbar.make(window.decorView.rootView, snackBarText, Snackbar.LENGTH_SHORT).show()
            }
        }


        binding.idbtnTambahdata.setOnClickListener {
            val note = binding.inputan.text.toString()
            if (note.isNotEmpty()) {
                mainApiViewModel.postNote(note)

//              Agar keyboard otomatis hilang
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