package com.example.travada.features.transfer.transferbank.pojo


import com.google.gson.annotations.SerializedName

data class GetNameResponse(
    @SerializedName("nama")
    val nama: String,
    @SerializedName("rekening")
    val rekening: String
)