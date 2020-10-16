package com.example.travada.features.tabungan.pojo


import com.google.gson.annotations.SerializedName

data class PostTabunganBody(
    @SerializedName("foto")
    val foto: String,
    @SerializedName("tabungan")
    val tabungan: Tabungan
) {
    data class Tabungan(
        @SerializedName("autodebet")
        val autodebet: Boolean,
        @SerializedName("gambar_tabungan")
        val gambarTabungan: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("jumlah_orang")
        val jumlahOrang: Int,
        @SerializedName("jumlah_setoran")
        val jumlahSetoran: Int,
        @SerializedName("jumlah_tabungan")
        val jumlahTabungan: Int,
        @SerializedName("periode")
        val periode: String,
        @SerializedName("setoran_awal")
        val setoranAwal: Int,
        @SerializedName("target")
        val target: String,
        @SerializedName("tujuan")
        val tujuan: String
    )
}