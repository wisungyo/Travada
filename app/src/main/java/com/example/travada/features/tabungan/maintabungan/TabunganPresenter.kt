package com.example.travada.features.tabungan.maintabungan

import androidx.appcompat.app.AppCompatActivity
import com.example.travada.features.tabungan.adapter.ListTabunganAdapter
import com.example.travada.features.tabungan.models.DataWisata
import com.example.travada.features.tabungan.network.ApiClientTabungan
import com.example.travada.features.tabungan.pojo.GetTabunganAll
import com.example.travada.features.tabungan.pojo.GetTabunganDetailResponse
import com.example.travada.features.tabungan.pojo.GetTabunganUserAll
import com.example.travada.util.util
import com.orhanobut.hawk.Hawk
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TabunganPresenter(val listener: Listener): AppCompatActivity() {

    fun dataTabunganList(){
        listener.showLoadingDialog()
        ApiClientTabungan.TP_API_SERVICES.getTabunganUserAll(Hawk.get(util.SF_TOKEN, "")).enqueue(object : Callback<GetTabunganUserAll> {
            override fun onFailure(call: Call<GetTabunganUserAll>, t: Throwable) {
                t.message?.let {
                    listener.implementAllTabunganFailure(it)
                }
                listener.hideLoadingDialog()
            }

            override fun onResponse(call: Call<GetTabunganUserAll>, response: Response<GetTabunganUserAll>) {
                response.body()?.data?.let {
                    listener.implementAllTabungan(it.toMutableList()) }
                listener.hideLoadingDialog()
            }
        })
    }

    fun goToDetailTabunganWisata(dataWisata: DataWisata) {
        listener.showDetailTabunganWisata(dataWisata)
    }

    fun itemClicked(idTabungan: Int) {
        listener.showItemClicked(idTabungan)
    }

    interface Listener {
        fun showItemClicked(idTabungan: Int)
        fun hideLoadingDialog()
        fun implementAllTabungan(getTabungan: MutableList<GetTabunganUserAll.Data>)
        fun implementAllTabunganFailure(errMessage: String)
        fun showLoadingDialog()
        fun showData(adapterWisataPilihan: ListTabunganAdapter)
        fun showDetailTabunganWisata(dataWisata: DataWisata)
    }
}