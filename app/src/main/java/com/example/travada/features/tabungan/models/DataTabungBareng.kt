package com.example.travada.features.tabungan.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataTabungBareng(
    val nama: String,
    val nomorRekening: String,
    val inisial : String
) : Parcelable