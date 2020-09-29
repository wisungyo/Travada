package com.example.travada.fragmentnav.riwayat.fragmentriwayat

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import com.example.travada.fragmentnav.riwayat.adapter.AdapterRiwayatProses
import com.example.travada.fragmentnav.riwayat.adapter.AdapterRiwayatStatus
import com.example.travada.fragmentnav.riwayat.network.ApiClientRiwayat
import com.example.travada.fragmentnav.riwayat.pojo.GetPemesananRiwayatResponse
import com.example.travada.sampeldata.DataRiwayat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StatusFragmentPresenter (val listener: Listener, val listenerAdapter: ListenerAdapter): AppCompatActivity() {

    fun fetchDataRiwayat() {
        val token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1IiwiaWF0IjoxNjAxMTA1MTY1LCJleHAiOjE2MDE3MDk5NjV9.3Yaxr1CgyZ47rEj2npIVKbfCT0dzzYh9FylLuqx_xt_aGFDcCvAICDNFUHaYZJhj838M8pJPZZBRplCg7sogyw"
        ApiClientRiwayat.API_SERVICE_RIWAYAT.getPemesanan(token).enqueue(object : Callback<GetPemesananRiwayatResponse> {
            override fun onResponse(
                call: Call<GetPemesananRiwayatResponse>,
                response: Response<GetPemesananRiwayatResponse>
            ) {
                if (!response.isSuccessful) {
                    listener.showDataError(response.code().toString())
                    return
                }

                response.body()?.data?.let {
                    val listPemesanan = ArrayList<GetPemesananRiwayatResponse.Data>()
                    for (i in 0..it.size-1) {
                        if (it[i].pemesanan.status == "disetujui" ||
                            it[i].pemesanan.status == "ditolak" ||
                            it[i].pemesanan.status == "expired") {
                                listPemesanan.add(it[i])
                        }
                    }
                    listener.showData(listPemesanan)
                }
            }

            override fun onFailure(call: Call<GetPemesananRiwayatResponse>, t: Throwable) {
                listener.showDataError(t.toString())
            }
        })
    }

    fun getDestinasiInfo(
        data: GetPemesananRiwayatResponse.Data,
        holder: AdapterRiwayatStatus.ViewHolder
    ) {
        TPApiClient.TP_API_SERVICES.getDestination(data.idDestinasi).enqueue(object : Callback<GetDestinasiResponse> {
            override fun onResponse(
                call: Call<GetDestinasiResponse>,
                response: Response<GetDestinasiResponse>
            ) {
                if (!response.isSuccessful) {
                    listener.showDataError(response.code().toString())
                    return
                }
                listenerAdapter.showData(response.body()?.data, data, holder)
            }

            override fun onFailure(call: Call<GetDestinasiResponse>, t: Throwable) {
                listener.showDataError(t.toString())
            }
        })
    }
    
    fun goToDetailRiwayat(idDestinasi: Int) {
        listener.showDetailRiwayat(idDestinasi)
    }

    interface Listener {
        fun showData(list: List<GetPemesananRiwayatResponse.Data>)
        fun showDetailRiwayat(idDestinasi: Int)
        fun showDataError(error: String)
    }

    interface ListenerAdapter {
        fun showData(
            dataInfo: GetDestinasiResponse.Data?,
            dataPemesananRiwayatResponse: GetPemesananRiwayatResponse.Data,
            holder: AdapterRiwayatStatus.ViewHolder
        )
    }
}