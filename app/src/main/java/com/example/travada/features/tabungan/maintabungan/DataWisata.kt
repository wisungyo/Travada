package com.example.travada.features.tabungan.maintabungan

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataWisata(
    val namaWisata: String,
    val biaya: String,
    var tempo: String,
    val gambar: Int
):Parcelable {
}