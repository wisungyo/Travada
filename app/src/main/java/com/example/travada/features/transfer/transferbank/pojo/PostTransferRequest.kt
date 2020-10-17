package com.example.travada.features.transfer.transferbank.pojo


import com.google.gson.annotations.SerializedName

data class PostTransferRequest(
    @SerializedName("catatan")
    val catatan: String,
    @SerializedName("nominal")
    val nominal: String,
    @SerializedName("pin")
    val pin: String,
    @SerializedName("rekeningTujuan")
    val rekeningTujuan: String
)