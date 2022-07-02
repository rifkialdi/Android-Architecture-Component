package com.example.androidarchitecturecomponent_dicoding.live_data_api_singleevent.api

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("notes")
	val notes: List<NotesItem>
)

data class NotesItem(

	@field:SerializedName("note")
	val note: String,

	@field:SerializedName("id")
	val id: String
)
