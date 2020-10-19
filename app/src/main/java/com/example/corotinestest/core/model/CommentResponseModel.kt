package com.example.corotinestest.core.model


import com.google.gson.annotations.SerializedName

class CommentResponseModel : ArrayList<CommentResponseModelItem>()

data class CommentResponseModelItem(
    @SerializedName("body")
    val body: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("postId")
    val postId: Int
)