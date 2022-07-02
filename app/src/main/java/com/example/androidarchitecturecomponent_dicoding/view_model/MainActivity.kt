package com.example.androidarchitecturecomponent_dicoding.view_model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.androidarchitecturecomponent_dicoding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* Menginisialisasi / Menyambung kelas ViewModel dengan MainActivity */
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        resultTotal()

        binding.idbtnTambahdata.setOnClickListener {
            val panjang = binding.idedtPanjang.text.toString()
            val lebar = binding.idedtLebar.text.toString()

            when {
                panjang.isEmpty() -> binding.idedtPanjang.error = "isi panjang dulu"
                lebar.isEmpty() -> binding.idedtPanjang.error = "isi panjang dulu"
                else -> {
                    /* mendapatkan value dan di proses di ViewModel*/
                    viewModel.calculate(panjang, lebar)
                    resultTotal()
                }
            }
        }
    }

    fun resultTotal() {
        /* TextView mendapatkan nilai dari properties ViewModel */
        binding.idtvResult.text = viewModel.result.toString()
    }
}