package com.example.travada.features.topup.pojo


import com.google.gson.annotations.SerializedName

data class GetTopupRequest(
    @SerializedName("nominal")
    val nominal: String,
    @SerializedName("pin")
    val pin: String,
    @SerializedName("rekening")
    val rekening: String
)