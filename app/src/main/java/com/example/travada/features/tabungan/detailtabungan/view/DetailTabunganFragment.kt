package com.example.travada.features.tabungan.detailtabungan.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.features.tabungan.adapter.DetailTabunganAdapter
import com.example.travada.features.tabungan.detailtabungan.presenter.DetailTabunganFragmentPresenter
import com.example.travada.features.tabungan.models.DataWisata
import com.example.travada.features.tabungan.pojo.GetTabunganDetailResponse
import com.example.travada.util.loadingdialog.LoadingDialog
import kotlinx.android.synthetic.main.fragment_detail_tabungan.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailTabunganFragment : Fragment(),
    DetailTabunganFragmentPresenter.Listener {

    private lateinit var presenter: DetailTabunganFragmentPresenter
    private lateinit var result : DataWisata

    val MyFragment = LoadingDialog()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_tabungan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idDetail = 1

        presenter =
            DetailTabunganFragmentPresenter(
                this
            )
        presenter.getDetailTabungan(idDetail)

//        arguments?.getParcelable<DataWisata>("detail")?.let {
//            result = it
//        }

    }

    override fun showLoadingDialog() {
        val fm=fragmentManager
        MyFragment.isCancelable = false
        fm?.let { MyFragment.show(it, "Fragment") }
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }

    override fun showData(adapterDetailTabungan: DetailTabunganAdapter) {
        val linearLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvDetailTabungan.adapter = adapterDetailTabungan
        rvDetailTabungan.layoutManager = linearLayout
    }

    override fun implementDetailTabungan(getTabungan: GetTabunganDetailResponse.Data) {

        if (getTabungan.gambarTabungan.isNotEmpty()) {
            Glide.with(this)
                .load(getTabungan.gambarTabungan)
                .circleCrop()
                .into(imageWisata)
        } else {
            Glide
                .with(this)
                .load( "https://cdn.thegeekdiary.com/wp-content/plugins/accelerated-mobile-pages/images/SD-default-image.png")
                .circleCrop()
                .into(imageWisata)
        }


        val df = DecimalFormat("#,###")
        df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
        tvBiaya.text = "Rp. ${df.format(getTabungan.jumlahSetoran)}"

        tvJumlahTabunngan.text = "dari Rp. ${df.format(getTabungan.jumlahTabungan)}"


        val tahunTarget         =  extractTahun(getTabungan.target)
        val bulanTarget         = extractBulan(getTabungan.target)
        val namaBulanDibuat     = changeBulan(bulanTarget)
        val tanggaltarget       =  extractTanggal(getTabungan.target)

        tvTanggalPencapaian.text = "$tanggaltarget $namaBulanDibuat $tahunTarget"

        tvTanggalPencapaian.text = getTabungan.target
        tvJumlahTabunngan.text = "dari Rp. ${df.format(getTabungan.setoranAwal)}"
    }

    override fun implementDetailTabunganFailure(errMessage: String) {
        Toast.makeText(context, "Error : $errMessage", Toast.LENGTH_LONG).show()
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

}