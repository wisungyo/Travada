package com.example.travada.detailriwayat.presenter

import androidx.appcompat.app.AppCompatActivity
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetUserInfo
import com.example.travada.sampeldata.SaldoSpinnerData
import com.example.travada.util.util
import com.orhanobut.hawk.Hawk
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BayarCicilanActivityPresenter (val listener: Listener): AppCompatActivity() {

    fun fetchData(id: Int) {
        var modelList = ArrayList<SaldoSpinnerData>()

//        listener.showLoadingDialog()
        TPApiClient.TP_API_SERVICES.getUserInfo(Hawk.get(util.SF_TOKEN, "")).enqueue(object :
            Callback<GetUserInfo> {
            override fun onResponse(call: Call<GetUserInfo>, response: Response<GetUserInfo>) {
                if (!response.isSuccessful) {
//                    listener.showDataError("Fetching data gagal")
                    return
                }

                response.body()?.data?.balance.let {
                    modelList.add(
                        SaldoSpinnerData("Saldo Aktif", it, "Saldo")
                    )
                }

                listener.showSpinner(modelList)
//                listener.showSaldo(response.body()?.data?.balance)
            }

            override fun onFailure(call: Call<GetUserInfo>, t: Throwable) {
//                listener.showDataError(t.toString())
            }

        })

    }

    fun doKonfirmasi(idCicilan: Int) {
        listener.showDialogKonfirmasi(
            "Konfirmasi Pembayaran",
            "Tagihan akan diambil dari saldo kamu"
        )
    }

    interface Listener {
//        fun showSpinner(
//            adapterSpinner: AdapterSpinnerBayarCicilan,
//            linearLayout: LinearLayoutManager
//        )
        fun readFromAsset(): List<SaldoSpinnerData>
        fun showSpinner(list: List<SaldoSpinnerData>)
        fun showDialogKonfirmasi(title: String, subtitle: String)
    }
}