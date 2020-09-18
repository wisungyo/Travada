package com.example.travada.welcomepage.pojo


import com.google.gson.annotations.SerializedName

data class PostResendResponse(
    @SerializedName("data")
    val `data`: Any,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)