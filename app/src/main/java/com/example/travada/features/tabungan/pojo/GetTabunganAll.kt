package com.example.travada.features.tabungan.pojo


import com.google.gson.annotations.SerializedName

data class GetTabunganAll(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String, // Success
    @SerializedName("status")
    val status: String // OK
) {
    data class Data(
        @SerializedName("id")
        val id: Int, // 8
        @SerializedName("judul_tabungan")
        val judulTabungan: String, // Malang Nasibmu
        @SerializedName("nama_nasabah")
        val namaNasabah: String, // Nanda Adi Setyadharma
        @SerializedName("nomor_rekening")
        val nomorRekening: String, // 08995269685
        @SerializedName("setoran")
        val setoran: Int, // 195652
        @SerializedName("status")
        val status: Any, // null
        @SerializedName("target")
        val target: Int, // 20000000
        @SerializedName("terkumpul")
        val terkumpul: Any // null
    )
}