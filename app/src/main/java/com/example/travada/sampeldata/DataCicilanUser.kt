package com.example.travada.sampeldata

data class DataCicilanUser(
    val id: Int,
    val name: String,
    val phone: String,
    val email: String,
    val uriKTP: String,
    val uriPassport: String,
    var status: Boolean
)