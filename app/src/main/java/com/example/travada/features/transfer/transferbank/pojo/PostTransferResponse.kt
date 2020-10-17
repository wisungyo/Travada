package com.example.travada.features.transfer.transferbank.pojo


import com.google.gson.annotations.SerializedName

data class PostTransferResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("bank_asal")
        val bankAsal: String,
        @SerializedName("bank_tujuan")
        val bankTujuan: String,
        @SerializedName("catatan")
        val catatan: String,
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("nama_asal")
        val namaAsal: String,
        @SerializedName("nama_tujuan")
        val namaTujuan: String,
        @SerializedName("nominal")
        val nominal: String,
        @SerializedName("rekening_asal")
        val rekeningAsal: String,
        @SerializedName("rekening_tujuan")
        val rekeningTujuan: String,
        @SerializedName("updatedAt")
        val updatedAt: String
    )
}