package com.example.travada.welcomepage.pojo


import com.google.gson.annotations.SerializedName

data class PostLoginResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("session")
        val session: String,
        @SerializedName("token")
        val token: String,
        @SerializedName("token_type")
        val tokenType: String
    )
}