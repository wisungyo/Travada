package com.example.travada.detailriwayat.presenter

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.detailriwayat.adapter.AdapterDetailRiwayatActivityDisetujui
import com.example.travada.detailriwayat.adapter.AdapterDetailRiwayatActivityDitolak
import com.example.travada.detailriwayat.adapter.AdapterDetailRiwayatActivityExpired
import com.example.travada.detailriwayat.adapter.AdapterDetailRiwayatActivityMenunggu
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import com.example.travada.features.rencana.pojo.GetPemesananDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailRiwayatActivityPresenter (val listener: Listener): AppCompatActivity() {

    fun fetchDestinasiData(idDestinasi: Int, idPemesanan: Int) {
        TPApiClient.TP_API_SERVICES.getDestination(idDestinasi).enqueue(object : Callback<GetDestinasiResponse> {
            override fun onResponse(
                call: Call<GetDestinasiResponse>,
                response: Response<GetDestinasiResponse>
            ) {
                if (!response.isSuccessful) {
                    //
                    return
                }

                listener.showDestinasiData(response.body()?.data)
                getPemesananData(idPemesanan)
            }

            override fun onFailure(call: Call<GetDestinasiResponse>, t: Throwable) {
//                TODO("Not yet implemented")
            }

        })
    }

    fun getPemesananData(idPemesanan: Int) {
        TPApiClient.TP_API_SERVICES.getPemesananDetail(idPemesanan).enqueue(object : Callback<GetPemesananDetailResponse> {
            override fun onResponse(
                call: Call<GetPemesananDetailResponse>,
                response: Response<GetPemesananDetailResponse>
            ) {
                listener.showPemesananDataOnDestinasiData(response.body()?.data)
            }

            override fun onFailure(call: Call<GetPemesananDetailResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun fetchCicilanData(idPemesanan: Int) {

        TPApiClient.TP_API_SERVICES.getPemesananDetail(idPemesanan).enqueue(object : Callback<GetPemesananDetailResponse> {
            override fun onResponse(
                call: Call<GetPemesananDetailResponse>,
                response: Response<GetPemesananDetailResponse>
            ) {
                if (!response.isSuccessful) {
                    // show error here
                    return
                }

                when (response.body()?.data?.pemesanan?.status) {
                    "menunggu" -> {
                        response.body()?.data?.let { getAdapterDetailPemesananMenunggu(it) }
                    }
                    "ditolak" -> {
                        response.body()?.data?.let { getAdapterDetailPemesananDitolak(it) }
                    }
                    "disetujui" -> {
                        response.body()?.data?.cicilan?.let { getAdapterDetailPemesananDisetujui(it) }
                    }
                    "expired" -> {
                        response.body()?.data?.let { getAdapterDetailPemesananExpired(it) }
                    }
                }

            }

            override fun onFailure(call: Call<GetPemesananDetailResponse>, t: Throwable) {
//                TODO("Not yet implemented")
            }

        })
    }

    fun getAdapterDetailPemesananDisetujui(data: List<GetPemesananDetailResponse.Data.Cicilan>) {
        val adapterDetailRiwayat = AdapterDetailRiwayatActivityDisetujui(data, this)
        val linearLayoutDetailRiwayat = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        listener.showData(adapterDetailRiwayat, linearLayoutDetailRiwayat)
    }

    fun getAdapterDetailPemesananDitolak(data: GetPemesananDetailResponse.Data) {
        val adapterDetailRiwayat = AdapterDetailRiwayatActivityDitolak(data, this)
        val linearLayoutDetailRiwayat = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        listener.showData(adapterDetailRiwayat, linearLayoutDetailRiwayat)
    }

    fun getAdapterDetailPemesananMenunggu(data: GetPemesananDetailResponse.Data) {
        val adapterDetailRiwayat = AdapterDetailRiwayatActivityMenunggu(data, this)
        val linearLayoutDetailRiwayat = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        listener.showData(adapterDetailRiwayat, linearLayoutDetailRiwayat)
    }

    fun getAdapterDetailPemesananExpired(data: GetPemesananDetailResponse.Data) {
        val adapterDetailRiwayat = AdapterDetailRiwayatActivityExpired(data, this)
        val linearLayoutDetailRiwayat = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        listener.showData(adapterDetailRiwayat, linearLayoutDetailRiwayat)
    }

    fun goToBayarCicilan() {
        listener.showBayarCicilan()
    }

    interface Listener {
        fun showData(
            adapterDetailRiwayatActivity: AdapterDetailRiwayatActivityDisetujui,
            linearLayoutDetailRiwayatActivity: LinearLayoutManager)
        fun showData(
            adapterDetailRiwayatActivity: AdapterDetailRiwayatActivityDitolak,
            linearLayoutDetailRiwayatActivity: LinearLayoutManager)
        fun showData(
            adapterDetailRiwayatActivity: AdapterDetailRiwayatActivityMenunggu,
            linearLayoutDetailRiwayatActivity: LinearLayoutManager)
        fun showData(
            adapterDetailRiwayatActivity: AdapterDetailRiwayatActivityExpired,
            linearLayoutDetailRiwayatActivity: LinearLayoutManager)
        fun showDestinasiData(data: GetDestinasiResponse.Data?)
        fun showPemesananDataOnDestinasiData(data: GetPemesananDetailResponse.Data?)
        fun showBayarCicilan()
    }
}