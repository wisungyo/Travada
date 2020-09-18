package com.example.travada.welcomepage.pojo


import com.google.gson.annotations.SerializedName

data class PostConfirmResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("token")
        val token: String,
        @SerializedName("token_type")
        val tokenType: String
    )
}