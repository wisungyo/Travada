package com.example.travada.sampeldata

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataRiwayat(
    val id: String,
    val img: Int,
    val title: String,
    val startDate: String,
    val endDate: String,
    val bookingDate: String,
    val money: String,
    val dp: String,
    val cicilanval: String,
    val member: Int,
    val status: String
) : Parcelable