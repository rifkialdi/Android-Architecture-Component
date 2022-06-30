package com.example.androidarchitecturecomponent_dicoding.live_data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.androidarchitecturecomponent_dicoding.R
import com.example.androidarchitecturecomponent_dicoding.databinding.ActivityMainLdactivityBinding

class MainLDActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainLdactivityBinding
    private lateinit var mLiveDataTimerViewModel: MainLDViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainLdactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* Inisialisasi viewModel */
        mLiveDataTimerViewModel = ViewModelProvider(this)[MainLDViewModel::class.java]

        subscribe()
    }

    private fun subscribe() {
        /* membuat objek Objek observer untuk di lampiirkan ke objek LiveData */
        val elapsedTimeObserver = Observer<Long?> { aLong ->
            val newText = this@MainLDActivity.resources.getString(R.string.seconds, aLong)
            binding.idtvPercentege.text = newText
        }

        /* objek observer di lampirkan ke objek LiveData menggunakan metode observe() */
        mLiveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
    }
}