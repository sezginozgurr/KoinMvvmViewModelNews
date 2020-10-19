package com.example.corotinestest.core.model


import com.google.gson.annotations.SerializedName

class AlbumsResponseModel : ArrayList<AlbumsItem>()

data class AlbumsItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)