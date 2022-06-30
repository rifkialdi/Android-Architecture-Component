package com.example.androidarchitecturecomponent_dicoding.live_data_api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidarchitecturecomponent_dicoding.databinding.ItemListLivedataapiBinding

class ApiAdapter(val listItem: ArrayList<String>) : RecyclerView.Adapter<ApiAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemListLivedataapiBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemListLivedataapiBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.idtvItemlist.text = listItem[position]
    }

    override fun getItemCount(): Int {
        return listItem.size
    }
}