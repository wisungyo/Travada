package com.example.travada.features.mutasi.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.mutasi.adapter.AdapterResultMutasiActivity
import com.example.travada.features.mutasi.presenter.ResultMutasiActivityPresenter
import com.gkemon.XMLtoPDF.PdfGenerator
import com.gkemon.XMLtoPDF.PdfGeneratorListener
import com.gkemon.XMLtoPDF.model.FailureResponse
import com.gkemon.XMLtoPDF.model.SuccessResponse
import kotlinx.android.synthetic.main.activity_result_mutasi.*


class ResultMutasiActivity : AppCompatActivity(), ResultMutasiActivityPresenter.Listener {
    lateinit var presenter: ResultMutasiActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_mutasi)
        presenter = ResultMutasiActivityPresenter(this)

        val mutasi      = intent.getStringExtra("MUTASI")
        val tglAwal     = intent.getStringExtra("TGL_AWAL")
        val tglAkhir    = intent.getStringExtra("TGL_AKHIR")
        val namaLengkap = intent.getStringExtra("MUTASI_NAMA")
        val rekening    = intent.getStringExtra("MUTASI_REK")

        presenter.fetchUserData(namaLengkap, rekening)

        when (mutasi) {
            "minggu" -> {
                presenter.fetchDataMinggu()
                fetchPeriodeMutasi("1 Minggu Terakhir")
            }
            "bulan" -> {
                presenter.fetchDataBulan()
                fetchPeriodeMutasi("1 Bulan Terakhir")
            }
            "tahun" -> {
                presenter.fetchDataTahun()
                fetchPeriodeMutasi("1 Tahun Terakhir")
            }
            "tanggal" -> {
                presenter.fetchDataTanggal(tglAwal, tglAkhir)
                fetchPeriodeMutasi("${tglAwal} - ${tglAkhir}")
            }
            else -> {
                presenter.fetchDataBulan() // set default to Bulanan
            }
        }

        iv_result_mutasi_back.setOnClickListener {
            finish()
        }

        btn_result_mutasi.setOnClickListener { 
            presenter.doConvertPDF()

//            val intentPDF = Intent(this, PDFMutasiActivity::class.java)
//            startActivity(intentPDF)
        }
    }

    fun fetchPeriodeMutasi(periode: String) {
        tv_result_mutasi_periode_tanggal.text = periode
    }

    override fun showUserData(namaLengkap: String?, rekening: String?) {
        tv_result_mutasi_username.text = namaLengkap
        tv_result_mutasi_rekening.text = rekening
    }

    override fun showData(adapter: AdapterResultMutasiActivity, linearLayout: LinearLayoutManager) {
        rv_result_mutasi.adapter        = adapter
        rv_result_mutasi.layoutManager  = linearLayout
    }

    override fun showConvertPDF() {
        PdfGenerator.getBuilder()
            .setContext(this)
            .fromViewIDSource()
            .fromViewID(this, R.id.cl_result_mutasi_nested_view)
//            .setDefaultPageSize(PdfGenerator.PageSize.A4)
            .setCustomPageSize(
                cl_result_mutasi_nested_view.width,
                cl_result_mutasi_nested_view.height
            )
            .setFileName("Test-PDF")
            .setFolderName("Test-PDF-Folder")
            .openPDFafterGeneration(true)
            .build(object : PdfGeneratorListener() {
                override fun onFailure(failureResponse: FailureResponse) {
                    super.onFailure(failureResponse)
                }

                override fun showLog(log: String) {
                    super.showLog(log)
                }

                override fun onSuccess(response: SuccessResponse) {
                    super.onSuccess(response)
                }
            })
    }
}