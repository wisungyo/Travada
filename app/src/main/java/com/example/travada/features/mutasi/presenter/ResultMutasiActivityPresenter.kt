package com.example.travada.features.mutasi.presenter

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.features.mutasi.adapter.AdapterResultMutasiActivity
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetMutasiFilter
import com.example.travada.sampeldata.DataMutasi
import com.example.travada.util.util
import com.orhanobut.hawk.Hawk
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ResultMutasiActivityPresenter (val listener: Listener): AppCompatActivity() {

    fun fetchUserData(namaLengkap: String?, rekening: String?) {
        listener.showUserData(namaLengkap, rekening)
    }

    fun sendDataToAdapter(data: List<GetMutasiFilter.Data>?) {
        val adapter         = AdapterResultMutasiActivity(data, this)
        val linearLayout    = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        listener.showData(adapter, linearLayout)
    }

    fun fetchDataMinggu() {
        TPApiClient.TP_API_SERVICES.getMutasiMingguan(Hawk.get(util.SF_TOKEN, "")).enqueue(object : Callback<GetMutasiFilter> {
            override fun onResponse(
                call: Call<GetMutasiFilter>,
                response: Response<GetMutasiFilter>
            ) {
                if (!response.isSuccessful) {
                    // error
                    return
                }

                sendDataToAdapter(response.body()?.data)
            }

            override fun onFailure(call: Call<GetMutasiFilter>, t: Throwable) {
//                TODO("Not yet implemented")
            }

        })
    }

    fun fetchDataBulan() {
        TPApiClient.TP_API_SERVICES.getMutasiBulanan(Hawk.get(util.SF_TOKEN, "")).enqueue(object : Callback<GetMutasiFilter> {
            override fun onResponse(
                call: Call<GetMutasiFilter>,
                response: Response<GetMutasiFilter>
            ) {
                if (!response.isSuccessful) {
                    // error
                    return
                }

                sendDataToAdapter(response.body()?.data)
            }

            override fun onFailure(call: Call<GetMutasiFilter>, t: Throwable) {
//                TODO("Not yet implemented")
            }

        })
    }

    fun fetchDataTahun() {
        TPApiClient.TP_API_SERVICES.getMutasiTahunan(Hawk.get(util.SF_TOKEN, "")).enqueue(object : Callback<GetMutasiFilter> {
            override fun onResponse(
                call: Call<GetMutasiFilter>,
                response: Response<GetMutasiFilter>
            ) {
                if (!response.isSuccessful) {
                    // error
                    return
                }

                sendDataToAdapter(response.body()?.data)
            }

            override fun onFailure(call: Call<GetMutasiFilter>, t: Throwable) {
//                TODO("Not yet implemented")
            }

        })
    }

    fun fetchDataTanggal(tglAwal: String?, tglAkhir: String?) {
        val tglAwalSplit = tglAwal?.split(" ")?.toMutableList()
        tglAwalSplit?.set(1, when (tglAwalSplit[1]) {
            "Jan" -> "01"
            "Feb" -> "02"
            "Mar" -> "03"
            "Apr" -> "04"
            "Mei" -> "05"
            "Jun" -> "06"
            "Jul" -> "07"
            "Agu" -> "08"
            "Sep" -> "09"
            "Okt" -> "10"
            "Nov" -> "11"
            "Des" -> "12"
            else  -> ""
        }
        )

        val tglAkhirSplit = tglAkhir?.split(" ")?.toMutableList()
        tglAkhirSplit?.set(1, when (tglAkhirSplit[1]) {
            "Jan" -> "01"
            "Feb" -> "02"
            "Mar" -> "03"
            "Apr" -> "04"
            "Mei" -> "05"
            "Jun" -> "06"
            "Jul" -> "07"
            "Agu" -> "08"
            "Sep" -> "09"
            "Okt" -> "10"
            "Nov" -> "11"
            "Des" -> "12"
            else  -> ""
        }
        )

        val sendTglAwal     = "${tglAwalSplit?.get(2)}-${tglAwalSplit?.get(1)}-${tglAwalSplit?.get(0)}"
        val sendTglAkhir    = "${tglAkhirSplit?.get(2)}-${tglAkhirSplit?.get(1)}-${tglAkhirSplit?.get(0)}"
        TPApiClient.TP_API_SERVICES.getMutasiCustom(Hawk.get(util.SF_TOKEN, ""), sendTglAwal, sendTglAkhir).enqueue(object : Callback<GetMutasiFilter> {
            override fun onResponse(
                call: Call<GetMutasiFilter>,
                response: Response<GetMutasiFilter>
            ) {
                if (!response.isSuccessful) {
                    // error
                    return
                }

                sendDataToAdapter(response.body()?.data)
            }

            override fun onFailure(call: Call<GetMutasiFilter>, t: Throwable) {
//                TODO("Not yet implemented")
            }

        })
    }

    fun doConvertPDF() {
        listener.showConvertPDF()
    }

    interface Listener {
        fun showUserData(namaLengkap: String?, rekening: String?)
        fun showData(adapter: AdapterResultMutasiActivity, linearLayout: LinearLayoutManager)
        fun showConvertPDF()
    }
}