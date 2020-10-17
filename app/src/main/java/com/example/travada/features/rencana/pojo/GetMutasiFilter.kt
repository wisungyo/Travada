package com.example.travada.features.rencana.pojo


import com.google.gson.annotations.SerializedName

data class GetMutasiFilter(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String, // Pengambilan data berhasil
    @SerializedName("status")
    val status: String // OK
) {
    data class Data(
        @SerializedName("bank_asal")
        val bankAsal: String, // Binar
        @SerializedName("bank_tujuan")
        val bankTujuan: String, // Travada
        @SerializedName("catatan")
        val catatan: Any, // null
        @SerializedName("createdAt")
        val createdAt: String, // 2020-10-16T12:13:31.534+00:00
        @SerializedName("id")
        val id: Int, // 1
        @SerializedName("nama_asal")
        val namaAsal: String, // Travada Testing
        @SerializedName("nama_tujuan")
        val namaTujuan: String, // Saldo Aktif
        @SerializedName("nominal")
        val nominal: String, // 75000
        @SerializedName("rekening_asal")
        val rekeningAsal: String, // 08113811707
        @SerializedName("rekening_tujuan")
        val rekeningTujuan: String, // 085868794013
        @SerializedName("updatedAt")
        val updatedAt: String // 2020-10-16T12:13:31.534+00:00
    )
}