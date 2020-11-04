package com.example.travada.welcomepage.pojo


import com.google.gson.annotations.SerializedName

data class PostLoginRequest(
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)