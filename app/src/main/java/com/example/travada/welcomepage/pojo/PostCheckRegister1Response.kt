package com.example.travada.welcomepage.pojo


import com.google.gson.annotations.SerializedName

data class PostCheckRegister1Response(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("msg1")
        val msg1: String?,
        @SerializedName("msg2")
        val msg2: String?,
        @SerializedName("msg3")
        val msg3: String?,
        @SerializedName("msg4")
        val msg4: String?
    )
}