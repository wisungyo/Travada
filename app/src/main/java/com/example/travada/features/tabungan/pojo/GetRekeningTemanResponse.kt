package com.example.travada.features.tabungan.pojo


import com.google.gson.annotations.SerializedName

data class GetRekeningTemanResponse(
    @SerializedName("nama")
    val nama: String,
    @SerializedName("rekening")
    val rekening: String
)