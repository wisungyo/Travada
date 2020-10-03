package com.example.travada.features.rencana.wisnu.view

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64   /*  NEED TO IMPORT IT MANUALLY. DAMN !!  */
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.person.TPPersonActivity
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import com.example.travada.features.rencana.pojo.PostPemesananBase64Request
import com.example.travada.features.rencana.pojo.PostPemesananResponse
import com.example.travada.features.rencana.wisnu.adapter.AdapterKonfirmasiRencanaActivity
import com.example.travada.features.rencana.wisnu.presenter.KonfirmasiRencanaActivityPresenter
import com.example.travada.sampeldata.DataCicilanUser
import com.example.travada.util.loadingdialog.LoadingDialog
import com.example.travada.util.util
import kotlinx.android.synthetic.main.activity_konfirmasi_rencana.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import kotlin.collections.ArrayList
import com.orhanobut.hawk.Hawk


class KonfirmasiRencanaActivity : AppCompatActivity(), KonfirmasiRencanaActivityPresenter.Listener {
    private lateinit var presenter: KonfirmasiRencanaActivityPresenter
    private val SECOND_ACTIVITY_REQUEST_CODE = 1
    val MyFragment= LoadingDialog()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi_rencana)
        presenter = KonfirmasiRencanaActivityPresenter(this)

        val intentIdDestinasi = intent.getIntExtra("DESTINASI_ID", 3)
        val intentJumlahOrang = intent.getIntExtra("JUMLAH_ORANG", 1)
        val intentJumlahBiaya = intent.getIntExtra("JUMLAH_BIAYA", 0)

        presenter.fetchMainData(intentIdDestinasi, intentJumlahOrang)
        // to declare recycler view according to how many people. only for the first time.
        presenter.fetchDetailPemesanan(intentJumlahOrang, intentJumlahBiaya)
        // to show the  layout of detail pemesanan
        presenter.fetchDetailPemesananLayout()
        presenter.checkNextButtonCondition()

        iv_konfirmasi_rencana_back.setOnClickListener {
            presenter.doBack()
        }

        btn_konfirmas_rencana.setOnClickListener {
            presenter.nextButtonClicked()
        }
    }

    fun showKonfirmasiYes() {
        presenter.postPemesananData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val name = data?.getStringExtra("name")
                val phone = data?.getStringExtra("phone")
                val email = data?.getStringExtra("email")
                val uriKTP = data?.getStringExtra("uriKTP")
                val uriPassport = data?.getStringExtra("uriPassport")
                val id = data?.getIntExtra("id", 1)

                if (id != null && name != null && phone != null && email != null && uriKTP != null && uriPassport != null) {
                    presenter.updateList(
                        id,
                        name,
                        phone,
                        email,
                        uriKTP,
                        uriPassport
                    )
                }

                presenter.fetchDetailPemesananLayout()
                presenter.checkNextButtonCondition()
            }
        }
    }

    override fun showMainData(data: GetDestinasiResponse.Data, jumlahOrang: Int) {
        // get the image from API
        if (data.gambarList.isNotEmpty()) {
            Glide
                .with(this)
                .load(data.gambarList[0])
                .centerCrop()
                .into(iv_konfirmasi_rencana)
        } else {
            Glide
                .with(this)
                .load("https://cdn.thegeekdiary.com/wp-content/plugins/accelerated-mobile-pages/images/SD-default-image.png")
                .centerCrop()
                .into(iv_konfirmasi_rencana)
        }

        // get the title from API
        tv_konfirmasi_rencana_topbar.text = data.namaTrip
        tv_konfirmasi_rencana_title.text = data.namaTrip

        // get the people from API
        val intentJumlahOrang = intent.getIntExtra("JUMLAH_ORANG", 1)
        tv_konfirmasi_rencana_member.text = "Jumlah : ${intentJumlahOrang} orang"
        
        // get the schedule from API
        tv_konfirmasi_rencana_date.text = "${data.berangkat} - ${data.pulang}"

        // get the total member
        tv_konfirmasi_rencana_jumlah_orang.text = "${jumlahOrang} orang"
    }

    override fun showDataCicilan(jumlahOrang: Int, jumlahBiaya: Int) {
        tv_konfirmasi_rencana_jumlah_orang.text = "$jumlahOrang orang"

        val df = DecimalFormat("#,###")
        df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
        tv_konfirmasi_rencana_jumlah_biaya.text = "Rp. ${df.format(jumlahBiaya)}"
    }

    override fun showDetailOrang(orang: Int) {
        val intentDetailOrang = Intent(this, TPPersonActivity::class.java)
        intentDetailOrang.putExtra("ORANG", orang)
        startActivityForResult(intentDetailOrang, SECOND_ACTIVITY_REQUEST_CODE)
    }

    override fun showCicilanList(
        adapterKonfirmasiRencanaActivity: AdapterKonfirmasiRencanaActivity,
        linearLayoutManager: LinearLayoutManager
    ) {
        rv_konfirmasi_rencana.adapter = adapterKonfirmasiRencanaActivity
        rv_konfirmasi_rencana.layoutManager = linearLayoutManager
    }

    override fun showNextButtonCondition(condition: Boolean) {
        if (!condition) {
            btn_konfirmas_rencana.isEnabled = false
            btn_konfirmas_rencana.isClickable = false
            btn_konfirmas_rencana.setBackgroundResource(R.drawable.bg_konfirmasi_rencana_btn_disable)
            btn_konfirmas_rencana.setTextColor(Color.parseColor("#777777"))
        } else {
            btn_konfirmas_rencana.isEnabled = true
            btn_konfirmas_rencana.isClickable = true
            btn_konfirmas_rencana.setBackgroundResource(R.drawable.bg_konfirmasi_rencana_btn)
            btn_konfirmas_rencana.setTextColor(Color.parseColor("#ffffff"))
        }
    }

    override fun showNextButtonClicked(title: String, subtitle: String) {
        DialogKonfirmasiPemesanan.newInstance(title, subtitle).show(supportFragmentManager, DialogKonfirmasiPemesanan.TAG)
    }

    override fun showResultRencana(data: PostPemesananResponse.Data) {
        val idDestinasi = intent.getIntExtra("DESTINASI_ID", 3)

        val intentResultRencana = Intent(this, ResultRencanaActivity::class.java)
        intentResultRencana.putExtra("ID_DESTINASI", idDestinasi)
        intentResultRencana.putExtra("ID_PEMESANAN", data.pemesanan.id)
        intentResultRencana.putExtra("ORANG", data.pemesanan.orang)
        intentResultRencana.putExtra("TOTAL", data.pemesanan.total)
        intentResultRencana.putExtra("TGL_PEMESANAN", data.pemesanan.createdAt)
        startActivity(intentResultRencana)
        finishAffinity()
    }

    override fun doPostPemesanan(listUser: ArrayList<DataCicilanUser>) {
        val idDestinasi = intent.getIntExtra("DESTINASI_ID", 3)
        val listNama = ArrayList<String>()
        val listEmail = ArrayList<String>()
        val listNoHP = ArrayList<String>()
        val listKTP = ArrayList<String>()
        val listPaspor = ArrayList<String>()

        for (i in 0..listUser.size-1) {
            val bitmapKTP: Bitmap = MediaStore.Images.Media.getBitmap(
                getContentResolver(), Uri.parse(
                    listUser[i].uriKTP
                )
            )
            val bitmapPassport: Bitmap = MediaStore.Images.Media.getBitmap(
                getContentResolver(), Uri.parse(
                    listUser[i].uriPassport
                )
            )

            val bos1 = ByteArrayOutputStream()
            bitmapKTP.compress(Bitmap.CompressFormat.JPEG, 100, bos1)
            val bos2 = ByteArrayOutputStream()
            bitmapPassport.compress(Bitmap.CompressFormat.JPEG, 100, bos2)

            val byteArrayKTP = bos1.toByteArray()
            val encodedStringKTP: String = Base64.encodeToString(byteArrayKTP, Base64.DEFAULT)
            val byteArrayPaspor = bos1.toByteArray()
            val encodedStringPaspor: String = Base64.encodeToString(byteArrayPaspor, Base64.DEFAULT)

            /*
            INSERT THE DATA */
            listNama.add(listUser[i].name)
            listEmail.add(listUser[i].email)
            listNoHP.add(listUser[i].phone)
            listKTP.add(encodedStringKTP)
            listPaspor.add(encodedStringPaspor)
        }

        val postPemesanan = PostPemesananBase64Request(
            idDestinasi,
            listUser.size,
            listNama,
            listEmail,
            listNoHP,
            listKTP,
            listPaspor
        )

        /*
        DINAMIC TOKEN & ID_USER */
        val token = Hawk.get(util.SF_TOKEN, "")
        val idUser = Hawk.get(util.SF_ID, 0)

        showLoadingDialog()
        TPApiClient.TP_API_SERVICES.postPemesanan(token, idUser, postPemesanan).enqueue(object :
            Callback<PostPemesananResponse> {
            override fun onResponse(
                call: Call<PostPemesananResponse>,
                response: Response<PostPemesananResponse>
            ) {
                if (!response.isSuccessful) {
                    showDataError("Mohon maaf ada kesalahan")
                    return
                }

                response.body()?.data?.let { showResultRencana(it) }
                hideLoadingDialog()
            }

            override fun onFailure(call: Call<PostPemesananResponse>, t: Throwable) {
                showDataError(t.message.toString())
                hideLoadingDialog()
            }

        })
    }

    override fun showDataError(localizedMessage: String?) {
        Toast.makeText(
            this,
            "Error : $localizedMessage",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showBack() {
        finish()
    }

    override fun showLoadingDialog() {
        val fm=supportFragmentManager
        MyFragment.isCancelable = false
        MyFragment.show(fm, "Fragment")
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }
}