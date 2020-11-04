package com.example.travada.features.mutasi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travada.R
import com.example.travada.features.mutasi.presenter.PDFMutasiActivityPresenter
import com.gkemon.XMLtoPDF.PdfGenerator
import com.gkemon.XMLtoPDF.PdfGeneratorListener
import com.gkemon.XMLtoPDF.model.FailureResponse
import com.gkemon.XMLtoPDF.model.SuccessResponse
import kotlinx.android.synthetic.main.activity_result_mutasi.*

class PDFMutasiActivity : AppCompatActivity(), PDFMutasiActivityPresenter.Listener {
    private lateinit var presenter: PDFMutasiActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_d_f_mutasi)
        presenter = PDFMutasiActivityPresenter(this)

//        presenter.setPDFLayout()

        tv_result_mutasi_topbar.setOnClickListener {
            presenter.setPDFLayout()
        }
    }

    override fun finishActivity() {
        finish()
    }

    override fun showPDF() {
        PdfGenerator.getBuilder()
            .setContext(this)
            .fromViewIDSource()
            .fromViewID(this, R.id.cl_result_mutasi_nested_view)
//            .setDefaultPageSize(PdfGenerator.PageSize.A4)
            .setCustomPageSize(
                cl_result_mutasi_nested_view.width,
                cl_result_mutasi_nested_view.height
            )
            .setFileName("Test-PDF-2")
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