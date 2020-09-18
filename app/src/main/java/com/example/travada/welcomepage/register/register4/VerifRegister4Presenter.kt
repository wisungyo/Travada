package com.example.travada.welcomepage.register.register4

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Bundle
import android.provider.MediaStore
import com.example.travada.welcomepage.network.ApiClient
import com.example.travada.welcomepage.pojo.PostLoginResponse
import com.example.travada.welcomepage.pojo.PostRegisterResponse
import com.google.gson.Gson
import okhttp3.Callback
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.ByteArrayOutputStream

class VerifRegister4Presenter(val listener: Listener) {

    fun chekRegisterFinal(Bundle: Bundle, bitmap1: Bitmap, bitmap2: Bitmap) {
        val builder: MultipartBody.Builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        lateinit var bundle:Bundle
        Bundle?.let {
            bundle = it
        }
        builder.addFormDataPart("nama_lengkap", bundle.getString("name").toString())
        builder.addFormDataPart("username", bundle.getString("username").toString())
        builder.addFormDataPart("email", bundle.getString("email").toString())
        builder.addFormDataPart("no_hp", bundle.getString("phone").toString())
        builder.addFormDataPart("no_rekening", bundle.getString("accountnumb").toString())
        builder.addFormDataPart("no_ktp", bundle.getString("KTPnumb").toString())
        builder.addFormDataPart("tgl_lahir", bundle.getString("birth").toString())
        builder.addFormDataPart("jenis_kelamin", bundle.getString("gender").toString())
        builder.addFormDataPart("pin", bundle.getString("pin").toString())
        builder.addFormDataPart("password", bundle.getString("password").toString())

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
        ApiClient.apiServices.registerfinal(builder.build()).enqueue(object : retrofit2.Callback<PostRegisterResponse> {
            override fun onFailure(call: Call<PostRegisterResponse>, t: Throwable) {
                listener.showToast(t.message.toString())
                listener.hideLoadingDialog()
            }

            override fun onResponse(
                call: Call<PostRegisterResponse>,
                response: Response<PostRegisterResponse>
            ) {

                if (response.isSuccessful) {
                    listener.goToNextPage(bundle)
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


    fun checket(verifpin: String, pin: String?) {
        if (verifpin.length == 6) {
            if (verifpin == pin) {
                listener.btnActive()
                listener.hideErrorMessage()
            } else {
                listener.ShowErrorMessage()
            }
        } else {
            listener.btnInactive()
            listener.hideErrorMessage()
        }
    }

    interface Listener {
        fun btnActive()
        fun btnInactive()
        fun ShowErrorMessage()
        fun hideErrorMessage()
        fun goToNextPage(bundle:Bundle)
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showToast(text: String)
    }
}