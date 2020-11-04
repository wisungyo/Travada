package com.example.travada.welcomepage.pojo
import com.google.gson.annotations.SerializedName

data class coba(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("autodebet")
        val autodebet: Boolean,
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("gambar_tabungan")
        val gambarTabungan: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("jumlah_setoran")
        val jumlahSetoran: Int,
        @SerializedName("jumlah_tabungan")
        val jumlahTabungan: Int,
        @SerializedName("nama")
        val nama: List<String>,
        @SerializedName("periode")
        val periode: String,
        @SerializedName("rekening")
        val rekening: List<String>,
        @SerializedName("setoran_awal")
        val setoranAwal: Int,
        @SerializedName("status")
        val status: Any,
        @SerializedName("target")
        val target: String,
        @SerializedName("terkumpul")
        val terkumpul: Int,
        @SerializedName("tujuan")
        val tujuan: String,
        @SerializedName("updatedAt")
        val updatedAt: String
    )
}