package com.example.travada.detailriwayat

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetPemesananDestinasiResponse
import com.example.travada.sampeldata.DataCicilan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRiwayatActivityPresenter (val listener: Listener): AppCompatActivity() {

    fun fetchDestinasiData(idDestinasi: Int) {
        val token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1IiwiaWF0IjoxNjAxMTA1MTY1LCJleHAiOjE2MDE3MDk5NjV9.3Yaxr1CgyZ47rEj2npIVKbfCT0dzzYh9FylLuqx_xt_aGFDcCvAICDNFUHaYZJhj838M8pJPZZBRplCg7sogyw"

        TPApiClient.TP_API_SERVICES.getPemesananDestinasi(token, idDestinasi).enqueue(object : Callback<GetPemesananDestinasiResponse> {
            override fun onResponse(
                call: Call<GetPemesananDestinasiResponse>,
                response: Response<GetPemesananDestinasiResponse>
            ) {
                if (!response.isSuccessful) {
                    //
                    return
                }

                listener.showDestinasiData(response.body()?.data)
            }

            override fun onFailure(call: Call<GetPemesananDestinasiResponse>, t: Throwable) {
//                TODO("Not yet implemented")
            }

        })
    }

    fun fetchCicilanData() {
        val listCicilan = arrayListOf(
            DataCicilan(
                "ABC123",
                "DP",
                2500000,
                "09 Juli 2020",
                "Menunggu Persetujuan"
            ),
            DataCicilan(
                "ABC123",
                "Cicilan 1/4",
                2500000,
                "09 Agustus 2020",
                "Dibayar"
            ),
            DataCicilan(
                "ABC123",
                "Cicilan 2/4",
                2500000,
                "09 September 2020",
                "Menunggu Pembayaran"
            ),
            DataCicilan(
                "ABC123",
                "Cicilan 3/4",
                2500000,
                "09 Oktober 2020",
                "Expired"
            ),
            DataCicilan(
                "ABC123",
                "Cicilan 4/4",
                2500000,
                "09 Agustus 2020",
                "Menunggu Pembayaran"
            )
        )

        val adapterDetailRiwayat = AdapterDetailRiwayatActivity(listCicilan, this)
        val linearLayoutDetailRiwayat = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        listener.showData(adapterDetailRiwayat, linearLayoutDetailRiwayat)
    }

    interface Listener {
        fun showData(
            adapterDetailRiwayatActivity: AdapterDetailRiwayatActivity,
            linearLayoutDetailRiwayatActivity: LinearLayoutManager)
        fun showDestinasiData(data: GetPemesananDestinasiResponse.Data?)
    }
}