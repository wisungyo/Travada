package com.example.travada.features.rencana.pojo


import com.google.gson.annotations.SerializedName

data class GetDestinasiDetailResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("benua")
        val benua: String,
        @SerializedName("berangkat")
        val berangkat: String,
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
        val pulang: String,
        @SerializedName("rencana_list")
        val rencanaList: List<String>,
        @SerializedName("syarat_ketentuan")
        val syaratKetentuan: String,
        @SerializedName("updatedAt")
        val updatedAt: String
    )
}