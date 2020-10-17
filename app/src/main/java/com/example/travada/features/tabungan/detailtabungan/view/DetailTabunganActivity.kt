package com.example.travada.features.tabungan.detailtabungan.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.travada.R
import com.example.travada.features.tabungan.adapter.PageAdapter
import com.example.travada.features.tabungan.detailtabungan.presenter.DetailTabunganPresenter
import com.example.travada.features.tabungan.pojo.GetTabunganDetailResponse
import com.example.travada.util.loadingdialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_detail_tabungan.*

class DetailTabunganActivity : AppCompatActivity(), DetailTabunganPresenter.Listener {
    lateinit var presenter : DetailTabunganPresenter
    val MyFragment = LoadingDialog()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tabungan)
        presenter = DetailTabunganPresenter(this)

        val intentId = intent.getIntExtra("TABUNGAN_ID", 3)

        presenter.getDetail(intentId)

        viewPagerMain.adapter = PageAdapter(supportFragmentManager)
        tabMain.setupWithViewPager(viewPagerMain)

        ivFormResultBack.setOnClickListener {
            finish()
        }
    }

    override fun implementDetailTabungan(getTabungan: GetTabunganDetailResponse.Data) {
        tvTitleTabunganDetail.text = getTabungan.tujuan
    }

    override fun showLoadingDialog() {
        val fm = supportFragmentManager
        MyFragment.isCancelable = false
        MyFragment.show(fm, "Fragment")
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }

    override fun implementDetailTabunganFailure(errMessage: String) {
        Toast.makeText(this, "Error : $errMessage", Toast.LENGTH_LONG).show()
    }
}