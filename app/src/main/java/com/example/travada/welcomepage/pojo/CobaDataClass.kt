package com.example.travada.welcomepage.pojo


import com.google.gson.annotations.SerializedName

data class CobaDataClass(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("pin")
        val pin: String,
        @SerializedName("token")
        val token: String,
        @SerializedName("token_type")
        val tokenType: String
    )
}