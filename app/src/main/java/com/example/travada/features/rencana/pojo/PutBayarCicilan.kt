package com.example.travada.features.rencana.pojo


import com.google.gson.annotations.SerializedName

data class PutBayarCicilan(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // Pembayaran cicilan dengan id 165 telah diterima
    @SerializedName("status")
    val status: String // OK
) {
    data class Data(
        @SerializedName("id")
        val id: Int, // 165
        @SerializedName("jatuh_tempo")
        val jatuhTempo: String, // 2020-12-17
        @SerializedName("jumlah")
        val jumlah: Int, // -400000
        @SerializedName("status")
        val status: String // Dibayar
    )
}