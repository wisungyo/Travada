package com.example.travada.welcomepage.pojo


import com.google.gson.annotations.SerializedName

data class PostConfirmRequest(
    @SerializedName("confirmation_code")
    val confirmationCode: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)