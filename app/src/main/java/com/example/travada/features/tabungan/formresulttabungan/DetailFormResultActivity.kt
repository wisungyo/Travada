package com.example.travada.features.tabungan.formresulttabungan

import android.content.Intent
import android.graphics.Bitmap
import android.icu.text.NumberFormat
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.tabungan.adapter.BarengTemanAdapter
import com.example.travada.features.tabungan.adapter.ListTabunganAdapter
import com.example.travada.features.tabungan.adapter.TabungBarengAdapter
import com.example.travada.features.tabungan.formtabungandua.FormTabunganTwoPresenter
import com.example.travada.features.tabungan.maintabungan.TabunganActivity
import com.example.travada.features.tabungan.models.DataTabungBareng
import com.example.travada.util.loadingdialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_detail_form_result.*
import kotlinx.android.synthetic.main.activity_form_tabungan_two.*
import kotlinx.android.synthetic.main.activity_verif_register4.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class DetailFormResultActivity : AppCompatActivity(), DetailFormResultPresenter.Listener {
    private lateinit var presenter: DetailFormResultPresenter
    private lateinit var bundle: Bundle
    val MyFragment = LoadingDialog()
    lateinit var listTabungBareng: ArrayList<DataTabungBareng>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_form_result)

        presenter = DetailFormResultPresenter(this)
        intent?.extras?.let { bundle = it }

        listTabungBareng = ArrayList<DataTabungBareng>()



        presenter.getMe()

        val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(
            getContentResolver(),
            Uri.parse(bundle.getString("uriGambar"))
        )


        Glide
            .with(this)
            .load(bundle.getString("uriGambar"))
            .into(imageWisata)




        ivFormResultBack.setOnClickListener {
            finish()
        }

//        intent.putExtra("uriGambar", uriGambar)
//        intent.putExtra("jumlahDitabung", jumlahDitabung)
//        intent.putExtra("namaTujuan", tujuan)
//        intent.putExtra("tanggalTarget", etTanggal.text.toString())
//        intent.putExtra("setoranAwal", etSetoranAwal.text.toString())
//        intent.putExtra("metodeTabungan", etMetodeTabungan.text.toString())
//        intent.putExtra("periodeTabungan", etPeriodeTabungan.text.toString())
//        intent.putExtra("jumlahSetoran", etJumlahSetoran.text.toString())

//        val namaTujuan = bundle.getString("namaTujuan")
        val namaTujuan = intent.getStringExtra("namaTujuan")
        tvNamaTujuan.setText(namaTujuan)

        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)

//        val jumlahDitabung = bundle.getString("jumlahDitabung")
        val jumlahDitabung = intent.getStringExtra("jumlahDitabung")?.replace(".","")?.toInt()
        tvJumlahDitabung.text = numberFormat.format(jumlahDitabung)

//        val setoranAwal = bundle.getString("setoranAwal")
        val setoranAwal = intent.getStringExtra("setoranAwal")?.replace(".","")?.toInt()
        tvSetoranAwal.text = numberFormat.format(setoranAwal)

//        val tabunganBulanan = bundle.getString("jumlahSetoran")
        val tabunganBulanan = intent.getStringExtra("jumlahSetoran")?.replace(".","")?.toInt()
        tvTabunganBulanan.text = numberFormat.format(tabunganBulanan)

//        val tanggalTarget = bundle.getString("tanggalTarget")
        val tanggalTarget = intent.getStringExtra("tanggalTarget")
        tvTanggalTarget.setText(tanggalTarget)

//        val listnama = intent.getStringArrayListExtra("namaTambah")
//        val listnorek = intent.getStringArrayListExtra("nomerRekeningTambah")
//        val listinisial = intent.getStringArrayListExtra("inisialTambah")
//
//        if (listnama != null) {
//            val to = listnama.size+1
//            for (value in 0..to) {
//                val body = DataTabungBareng(
//                    listnama.get(value).toString(),
//                    listnorek?.get(value).toString(),
//                    listinisial?.get(value).toString()
//                )
//
//                listTabungBareng.add(body)
//            }
//
//            val adapterTabungBareng = BarengTemanAdapter(listTabungBareng)
//            showDataTabungBareng(adapterTabungBareng)
//        }


        val listlist = intent.getParcelableArrayListExtra<DataTabungBareng>("DataList")
        if (listlist != null) {
                val adapterTabungBareng = BarengTemanAdapter(listlist)
                showDataTabungBareng(adapterTabungBareng)
        }



        btnBuatSekarang.setOnClickListener {
            if (listlist != null) {
                presenter.dataFinal1(bundle, bitmap, listlist)
            } else {
                presenter.dataFinal(bundle, bitmap)
            }

        }


    }

    override fun showLoadingDialog() {
        val fm = supportFragmentManager
        MyFragment.isCancelable = false
        MyFragment.show(fm, "Fragment")
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }

    override fun showToast(text: String) {
        Toast.makeText(
            this, text,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showDataTabungBareng(adapterTabungBareng: BarengTemanAdapter) {
        val layoutManagerLinear =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvDetail.layoutManager = layoutManagerLinear
        rvDetail.adapter = adapterTabungBareng
        rvDetail.overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun goTo() {
        val gotoMainTabungan = Intent(this, TabunganActivity::class.java)
        startActivity(gotoMainTabungan)
    }

    companion object {
        var akunsendiri = ""
        var noreksendiri = ""
    }
}