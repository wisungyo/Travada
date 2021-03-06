package com.example.travada.fragmentnav.riwayat.fragmentriwayat

import androidx.appcompat.app.AppCompatActivity
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import com.example.travada.fragmentnav.riwayat.adapter.AdapterRiwayatStatus
import com.example.travada.fragmentnav.riwayat.network.ApiClientRiwayat
import com.example.travada.fragmentnav.riwayat.pojo.GetPemesananResponse
import com.example.travada.util.util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.orhanobut.hawk.Hawk

class StatusFragmentPresenter (val listener: Listener, val listenerAdapter: ListenerAdapter): AppCompatActivity() {

    fun fetchDataRiwayat() {
        val token = Hawk.get(util.SF_TOKEN, "")
        listenerAdapter.showLoadingDialog()
        ApiClientRiwayat.API_SERVICE_RIWAYAT.getPemesananNew(token).enqueue(object : Callback<GetPemesananResponse> {
            override fun onResponse(
                call: Call<GetPemesananResponse>,
                response: Response<GetPemesananResponse>
            ) {
                if (!response.isSuccessful) {
                    listener.showDataError(response.code().toString())
                    return
                }

                response.body()?.data?.let {
                    val listPemesanan = ArrayList<GetPemesananResponse.Data>()
                    for (i in 0..it.size-1) {
                        if (it[i].pemesanan.status == "Disetujui" ||
                            it[i].pemesanan.status == "Ditolak" ||
                            it[i].statusDisetujui == "Expired") {
                                listPemesanan.add(it[i])
                        }
                    }
                    listener.showData(listPemesanan)
                }
                listenerAdapter.hideLoadingDialog()
            }

            override fun onFailure(call: Call<GetPemesananResponse>, t: Throwable) {
                listener.showDataError(t.toString())
                listenerAdapter.hideLoadingDialog()
            }
        })
    }

    fun getDestinasiInfo(
        data: GetPemesananResponse.Data,
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
    
    fun goToDetailRiwayat(idDestinasi: Int, idPemesanan: Int) {
        listener.showDetailRiwayat(idDestinasi, idPemesanan)
    }

    interface Listener {
        fun showData(list: ArrayList<GetPemesananResponse.Data>)
        fun showDetailRiwayat(idDestinasi: Int, idPemesanan: Int)
        fun showDataError(error: String)
    }

    interface ListenerAdapter {
        fun showData(
            dataInfo: GetDestinasiResponse.Data?,
            dataPemesananRiwayatResponse: GetPemesananResponse.Data,
            holder: AdapterRiwayatStatus.ViewHolder
        )
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun checkLoadingDialog(): Boolean
    }
}