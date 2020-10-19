package com.example.travada.features.tabungan.formresulttabungan

import android.graphics.Bitmap
import android.os.Bundle
import com.example.travada.features.tabungan.adapter.BarengTemanAdapter
import com.example.travada.features.tabungan.formresulttabungan.DetailFormResultActivity.Companion.akunsendiri
import com.example.travada.features.tabungan.formresulttabungan.DetailFormResultActivity.Companion.noreksendiri
import com.example.travada.features.tabungan.models.DataTabungBareng
import com.example.travada.features.tabungan.network.ApiClientTabungan
import com.example.travada.features.tabungan.pojo.PostTabunganResponse
import com.example.travada.fragmentnav.akun.network.AkunApiClient
import com.example.travada.fragmentnav.akun.pojo.GetUserMeResponse
import com.example.travada.util.util
import com.example.travada.welcomepage.network.WPApiClient
import com.example.travada.welcomepage.pojo.PostRegisterResponse
import com.google.gson.Gson
import com.orhanobut.hawk.Hawk
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.ByteArrayOutputStream

class DetailFormResultPresenter (val listener: Listener){

    fun dataFinal(Bundle: Bundle, bitmap: Bitmap) {
        val builder: MultipartBody.Builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        lateinit var bundle: Bundle
        Bundle?.let {
            bundle = it
        }


        val bos1 = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 25, bos1)

        builder.addFormDataPart(
            "foto", "photo1.jpg",
            RequestBody.create(MultipartBody.FORM, bos1.toByteArray())
        )

        builder.addFormDataPart("tujuan", bundle.getString("namaTujuan").toString())
        builder.addFormDataPart("jumlah_tabungan", bundle.getString("jumlahDitabung").toString())
        builder.addFormDataPart("target", bundle.getString("tanggalTarget").toString())
        builder.addFormDataPart("setoran_awal", bundle.getString("setoranAwal").toString())
        builder.addFormDataPart("autodebet", "true")
        builder.addFormDataPart("periode", bundle.getString("periodeTabungan").toString())
        builder.addFormDataPart("nama", akunsendiri)
        builder.addFormDataPart("rekening", noreksendiri)

        if (bundle.getString("namatambah").toString().isNotEmpty()) {
            builder.addFormDataPart("nama", bundle.getString("namatambah").toString())
            builder.addFormDataPart("rekening", bundle.getString("rekeningtambah").toString())
        }

        var token = Hawk.get(util.SF_TOKEN, "0")

        listener.showLoadingDialog()
        ApiClientTabungan.TP_API_SERVICES.createTabungan(token, builder.build()).enqueue(object : retrofit2.Callback<PostTabunganResponse> {
            override fun onFailure(call: Call<PostTabunganResponse>, t: Throwable) {
                listener.showToast(t.message.toString())
                listener.hideLoadingDialog()
            }

            override fun onResponse(
                call: Call<PostTabunganResponse>,
                response: Response<PostTabunganResponse>
            ) {
                if (response.isSuccessful) {
                    listener.goTo()
                    listener.hideLoadingDialog()
                } else {
                    val errorResponse: PostRegisterResponse = Gson().fromJson(
                        response.errorBody()?.string(),
                        PostRegisterResponse::class.java
                    )
                    listener.showToast(errorResponse.message)
                    listener.hideLoadingDialog()
                }
            }

        })
    }

    fun getMe() {
        var token = Hawk.get(util.SF_TOKEN, "0")


        listener.showLoadingDialog()
        AkunApiClient.AKUN_API_SERVICES.getMe(token)
            .enqueue(object : retrofit2.Callback<GetUserMeResponse> {
                override fun onFailure(call: Call<GetUserMeResponse>, t: Throwable) {
                    listener.showToast(t.message.toString())
                    listener.hideLoadingDialog()
                }

                override fun onResponse(
                    call: Call<GetUserMeResponse>,
                    response: Response<GetUserMeResponse>
                ) {
                    response.body()?.data?.let {
                        akunsendiri = it.noRekening
                        noreksendiri = it.namaLengkap
                    }
                    listener.hideLoadingDialog()
                }

            })
    }




    fun fetchTabungBarengData(){
        val listTabungBareng = arrayListOf(
            DataTabungBareng(
                "Nanda Adi",
                "1212131",
                "AN"
            ),
            DataTabungBareng(
                "Abigail",
                "1212122",
                "A"
            ),
            DataTabungBareng(
                "Nicholas",
                "3434343",
                "N"
            )
        )
        val adapterTabungBareng = BarengTemanAdapter(listTabungBareng)
        listener.showDataTabungBareng(adapterTabungBareng)

    }
    interface Listener{
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showToast(text: String)
        fun showDataTabungBareng(adapterTabungBareng: BarengTemanAdapter)
        fun goTo()
    }
}