package com.example.travada.welcomepage.pojo


import com.google.gson.annotations.SerializedName

data class PostResendRequest(
    @SerializedName("email")
    val email: String
)