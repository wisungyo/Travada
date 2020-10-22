package com.example.travada.features.rencana.pojo


import com.google.gson.annotations.SerializedName

data class GetTabunganUserAll(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String, // Data tabungan user dengan id 5 berhasil diambil
    @SerializedName("status")
    val status: String // OK
) {
    data class Data(
        @SerializedName("autodebet")
        val autodebet: Boolean, // true
        @SerializedName("createdAt")
        val createdAt: String, // 2020-10-17T08:24:27.782+00:00
        @SerializedName("gambar_tabungan")
        val gambarTabungan: String, // http://res.cloudinary.com/dxzkryz3x/image/upload/v1602923066/utjn90ubetjaw5caypah.jpg
        @SerializedName("id")
        val id: Int, // 7
        @SerializedName("jumlah_setoran")
        val jumlahSetoran: Int, // 195652
        @SerializedName("jumlah_tabungan")
        val jumlahTabungan: Int, // 20000000
        @SerializedName("nama")
        val nama: List<String>,
        @SerializedName("periode")
        val periode: String, // Mingguan
        @SerializedName("rekening")
        val rekening: List<String>,
        @SerializedName("setoran_awal")
        val setoranAwal: Int, // 1000000
        @SerializedName("status")
        val status: Any, // null
        @SerializedName("target")
        val target: String, // 2021-09-10
        @SerializedName("terkumpul")
        val terkumpul: Any, // null
        @SerializedName("tujuan")
        val tujuan: String, // Malang Nasibmu
        @SerializedName("updatedAt")
        val updatedAt: String // 2020-10-17T08:24:27.782+00:00
    )
}