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

        presenter.fetchData()

        btn_result_mutasi.setOnClickListener { 
            presenter.doConvertPDF()
        }
    }

    override fun showData(adapter: AdapterResultMutasiActivity, linearLayout: LinearLayoutManager) {
        rv_result_mutasi.adapter = adapter
        rv_result_mutasi.layoutManager = linearLayout
    }

    override fun showConvertPDF() {
        PdfGenerator.getBuilder()
            .setContext(this)
            .fromViewIDSource()
            .fromViewID(this, R.id.rv_result_mutasi, R.id.rv_result_mutasi)
            .setDefaultPageSize(PdfGenerator.PageSize.A4)
            .setFileName("Test-PDF")
            .setFolderName("Test-PDF-folder")
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