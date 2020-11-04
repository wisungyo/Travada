package com.example.travada.welcomepage.pojo


import com.google.gson.annotations.SerializedName

data class PostRegisterResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("email")
        val email: String,
        @SerializedName("username")
        val username: String
    )
}