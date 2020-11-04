package com.example.travada.features.rencana.pojo


import com.google.gson.annotations.SerializedName

data class PostPemesananBase64Request(
    @SerializedName("idDestinasi")
    val idDestinasi: Int, // 40
    @SerializedName("orang")
    val orang: Int, // 1
    @SerializedName("nama")
    val nama: List<String>,
    @SerializedName("email")
    val email: List<String>,
    @SerializedName("no_hp")
    val noHp: List<String>,
    @SerializedName("ktp")
    val ktp: List<String>,
    @SerializedName("paspor")
    val paspor: List<String>
)