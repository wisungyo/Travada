package com.example.travada.features.tabungan.formresulttabungan

import android.graphics.Bitmap
import android.os.Bundle
import com.example.travada.features.tabungan.adapter.BarengTemanAdapter
import com.example.travada.features.tabungan.models.DataTabungBareng
import com.example.travada.features.tabungan.network.ApiClientTabungan
import com.example.travada.features.tabungan.pojo.PostTabunganResponse
import com.example.travada.welcomepage.network.WPApiClient
import com.example.travada.welcomepage.pojo.PostRegisterResponse
import com.google.gson.Gson
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.ByteArrayOutputStream

class DetailFormResultPresenter (val listener: Listener){

    fun dataFinal(Bundle: Bundle, bitmap1: Bitmap, bitmap2: Bitmap) {
        val builder: MultipartBody.Builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        lateinit var bundle: Bundle
        Bundle?.let {
            bundle = it
        }
        builder.addFormDataPart("nama_lengkap", bundle.getString("name").toString())


        val bos1 = ByteArrayOutputStream()
        bitmap1.compress(Bitmap.CompressFormat.JPEG, 25, bos1)

        builder.addFormDataPart(
            "foto_ktp", "photo1.jpg",
            RequestBody.create(MultipartBody.FORM, bos1.toByteArray())
        )

        val bos2 = ByteArrayOutputStream()
        bitmap2.compress(Bitmap.CompressFormat.JPEG, 25, bos2)

        builder.addFormDataPart(
            "selfie_ktp", "photo2.jpg",
            RequestBody.create(MultipartBody.FORM, bos2.toByteArray())
        )

        listener.showLoadingDialog()
//        ApiClientTabungan.TP_API_SERVICES.createTabungan(builder.build()).enqueue(object : retrofit2.Callback<PostTabunganResponse> {
//            override fun onFailure(call: Call<PostTabunganResponse>, t: Throwable) {
//                listener.showToast(t.message.toString())
//                listener.hideLoadingDialog()
//            }
//
//            override fun onResponse(
//                call: Call<PostRegisterResponse>,
//                response: Response<PostRegisterResponse>
//            ) {
//
//                if (response.isSuccessful) {
//                    listener.goToNextPage(bundle)
//                    listener.hideLoadingDialog()
//                } else {
//                    val errorResponse: PostRegisterResponse = Gson().fromJson(
//                        response.errorBody()?.string(),
//                        PostRegisterResponse::class.java
//                    )
//                    listener.showToast(errorResponse.message)
//                    listener.hideLoadingDialog()
//                }
//            }
//        })
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
    }
}