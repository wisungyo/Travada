package com.example.travada.features.rencana.wisnu.presenter

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetCicilanResponse
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import com.example.travada.features.rencana.wisnu.adapter.AdapterPesanRencanaActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PesanRencanaActivityPresenter(val listener: Listener): AppCompatActivity() {

    fun fetchMainData(id: Int) {
        val arraySpinner = ArrayList<String>()
        listener.showLoadingDialog()
        TPApiClient.TP_API_SERVICES.getDestination(id).enqueue(object : Callback<GetDestinasiResponse> {
            override fun onResponse(
                call: Call<GetDestinasiResponse>,
                response: Response<GetDestinasiResponse>
            ) {
                if (response.isSuccessful && response.body()?.status == "OK") {
                    response.body()?.data?.let {
                        listener.showMainData(it)
                        for (i in 0..it.berangkat.size-1) {
                            val berangkatTahun      = extractTahun(it.berangkat[i].toString())
                            val berangkatBulan      = extractBulan(it.berangkat[i].toString())
                            val namaBulanBerangkat:String  = changeBulan(berangkatBulan)
                            val berangkatTanggal    = extractTanggal(it.berangkat[i].toString())
                            arraySpinner.add( "$berangkatTanggal $namaBulanBerangkat $berangkatTahun") // from api still not a List.
                        }

                        listener.showSpinner(arraySpinner)
                    }
                } else {
                    getDataError("Mohon maaf. Ada kesalahan (1).")
                }
//                listener.hideLoadingDialog()
            }

            override fun onFailure(call: Call<GetDestinasiResponse>, t: Throwable) {
                getDataError(t.localizedMessage)
//                listener.hideLoadingDialog()
            }

        })
    }

    fun extractTanggal(tanggal: String): String {
        return tanggal.subSequence(8,10).toString()
    }

    fun extractBulan(bulan: String): String {
        return bulan.subSequence(5,7).toString()
    }

    fun extractTahun(tahun: String): String {
        return tahun.subSequence(0,4).toString()
    }

    fun changeBulan(bulan: String): String {
        var namaBulan = ""
        when (bulan) {
            "01" -> namaBulan = "Januari"
            "02" -> namaBulan = "Februari"
            "03" -> namaBulan = "Maret"
            "04" -> namaBulan = "April"
            "05" -> namaBulan = "Mei"
            "06" -> namaBulan = "Juni"
            "07" -> namaBulan = "Juli"
            "08" -> namaBulan = "Agustus"
            "09" -> namaBulan = "September"
            "10" -> namaBulan = "Oktober"
            "11" -> namaBulan = "November"
            "12" -> namaBulan = "Desember"
        }
        return namaBulan
    }

    fun convertBulanToNumber(bulan: String): String {
        var angkaBulan = ""
        when (bulan) {
            "Januari"   -> angkaBulan = "01"
            "Februari"  -> angkaBulan = "02"
            "Maret"     -> angkaBulan = "03"
            "April"     -> angkaBulan = "04"
            "Mei"       -> angkaBulan = "05"
            "Juni"      -> angkaBulan = "06"
            "Juli"      -> angkaBulan = "07"
            "Agustus"   -> angkaBulan = "08"
            "September" -> angkaBulan = "09"
            "Oktober"   -> angkaBulan = "10"
            "November"  -> angkaBulan = "11"
            "Desember"  -> angkaBulan = "12"
        }
        return angkaBulan
    }

    fun fetchCicilanData(id: Int, jumlahOrang: Int, tglBerangkat: String) {
//        listener.showLoadingDialog()
        var tglBerangkatFix = ""

        if (tglBerangkat.length == 10) {
            tglBerangkatFix = tglBerangkat
            Log.d("CICILAN", "${tglBerangkat.length}")
        } else {
            val tgl     = tglBerangkat.subSequence(0,2)
            val bulan   = tglBerangkat.subSequence(3, tglBerangkat.length-5)
            val bulanAngka    = convertBulanToNumber(bulan as String)
            val tahun   = tglBerangkat.subSequence(tglBerangkat.length-4, tglBerangkat.length)
            Log.d("CICILAN", "-$tahun-$bulanAngka-$tgl-")
            tglBerangkatFix = "$tahun-$bulanAngka-$tgl"
            Log.d("CICILAN", "${tglBerangkat.length}")
        }

        TPApiClient.TP_API_SERVICES.getCicilan(id, jumlahOrang, tglBerangkatFix).enqueue(object : Callback<GetCicilanResponse> {
            override fun onResponse(
                call: Call<GetCicilanResponse>,
                response: Response<GetCicilanResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        getAdapterCicilan(it)

                        var jumlahBiaya = 0
                        for (i in 0..it.size-1) {
                            jumlahBiaya += kotlin.math.abs(it[i].jumlah)
                        }
                        listener.showJumlahBiaya(jumlahBiaya)
                    }
                } else {
                    getDataError("Mohon maaf. Ada kesalahan (2).")
                }
                listener.hideLoadingDialog()
            }

            override fun onFailure(call: Call<GetCicilanResponse>, t: Throwable) {
                getDataError(t.localizedMessage)
                listener.hideLoadingDialog()
            }
        })

    }

    fun getAdapterCicilan(data: List<GetCicilanResponse.Data>) {
        val adapterRencanaPesan = AdapterPesanRencanaActivity(data, this)
        val linearLayoutRencanaPesan = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        listener.showCicilanData(adapterRencanaPesan, linearLayoutRencanaPesan)
    }

    fun getDataError(localizedMessage: String?) {
        listener.showDataError(localizedMessage)
    }

    fun doBack() {
        listener.showBack()
    }

    fun addOrang(jumlahOrang: Int) {
        listener.showAddOrang(jumlahOrang + 1)
    }

    fun minOrang(jumlahOrang: Int) {
        var jumlahOrangNew = jumlahOrang - 1
        if (jumlahOrangNew <= 1) jumlahOrangNew = 1
        listener.showMinOrang(jumlahOrangNew)
    }

    fun doKonfirmasi(intentPosition: Int) {
        listener.showKonfirmasi(intentPosition)
    }


    /*
        INTERFACE
    */
    interface Listener {
        fun showMainData(data: GetDestinasiResponse.Data)
        fun showSpinner(arraySpinner: ArrayList<String>)
        fun showCicilanData(adapter: AdapterPesanRencanaActivity, layout: LinearLayoutManager)
        fun showDataError(localizedMessage: String?)
        fun showBack()
        fun showAddOrang(addOrang: Int)
        fun showMinOrang(addOrang: Int)
        fun showKonfirmasi(intentPosition: Int)
        fun showJumlahBiaya(jumlahBiaya: Int)
        fun showLoadingDialog()
        fun hideLoadingDialog()
    }
}