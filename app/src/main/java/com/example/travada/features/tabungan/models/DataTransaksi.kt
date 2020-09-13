package com.example.travada.features.tabungan.models

data class DataTransaksi(
    val image: String,
    val kegiatan: String,
    val jenisTransaksi: String,
    val nominalTransaksi: String,
    val tanggalTransaksi: String
) {
}