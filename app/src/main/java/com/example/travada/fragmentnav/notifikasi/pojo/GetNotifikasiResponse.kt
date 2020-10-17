package com.example.travada.fragmentnav.notifikasi.pojo


import com.google.gson.annotations.SerializedName

data class GetNotifikasiResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("destinasi")
        val destinasi: Destinasi,
        @SerializedName("id")
        val id: Int,
        @SerializedName("jenis")
        val jenis: String,
        @SerializedName("judul")
        val judul: String,
        @SerializedName("pemesanan")
        val pemesanan: Pemesanan,
        @SerializedName("pesan")
        val pesan: String,
        @SerializedName("terbaca")
        val terbaca: Boolean,
        @SerializedName("updatedAt")
        val updatedAt: String
    ) {
        data class Destinasi(
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
            val rencanaList: List<String>,
            @SerializedName("syarat_ketentuan")
            val syaratKetentuan: String,
            @SerializedName("updatedAt")
            val updatedAt: String
        )
        data class Pemesanan(
            @SerializedName("berangkat")
            val berangkat: String,
            @SerializedName("createdAt")
            val createdAt: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("orang")
            val orang: Int,
            @SerializedName("pulang")
            val pulang: String,
            @SerializedName("status")
            val status: String,
            @SerializedName("total")
            val total: Int,
            @SerializedName("updatedAt")
            val updatedAt: String
        )
    }
}