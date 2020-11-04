package com.example.travada.features.rencana.pojo


import com.google.gson.annotations.SerializedName

data class PutCicilan(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // status sukses diupdate
    @SerializedName("status")
    val status: String // OK
) {
    data class Data(
        @SerializedName("id")
        val id: Int, // 164
        @SerializedName("jatuh_tempo")
        val jatuhTempo: String, // 2020-11-17
        @SerializedName("jumlah")
        val jumlah: Int, // -400000
        @SerializedName("status")
        val status: String // Dibayar
    )
}