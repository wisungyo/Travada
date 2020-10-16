package com.example.travada.features.rencana.pojo


import com.google.gson.annotations.SerializedName

data class GetDestinasiAllResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("benua")
        val benua: String,
        @SerializedName("berangkat")
        val berangkat: List<String>,
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("deskripsi")
        val deskripsi: String,
        @SerializedName("durasi")
        val durasi: Int,
        @SerializedName("fasilitas")
        val fasilitas: List<String>,
        @SerializedName("gambar_list")
        val gambarList: List<String>,
        @SerializedName("harga_satuan")
        val hargaSatuan: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("info_kesehatan_keamanan")
        val infoKesehatanKeamanan: String,
        @SerializedName("info_persiapan")
        val infoPersiapan: String,
        @SerializedName("info_waktu_cuaca")
        val infoWaktuCuaca: String,
        @SerializedName("kapasitas")
        val kapasitas: Int,
        @SerializedName("kapasitas_terisi")
        val kapasitasTerisi: Int,
        @SerializedName("lokal")
        val lokal: Boolean,
        @SerializedName("nama_trip")
        val namaTrip: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("popularitas")
        val popularitas: Int,
        @SerializedName("pulang")
        val pulang: List<String>,
        @SerializedName("rencana_list")
        val rencanaList: List<Any>,
        @SerializedName("updatedAt")
        val updatedAt: String
    )
}