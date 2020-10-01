package com.example.travada.features.tabungan.maintabungan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.tabungan.adapter.ListWisataAdapter
import com.example.travada.features.tabungan.formtabungansatu.FormTabunganOneActivity
import com.example.travada.features.tabungan.models.DataWisata
import kotlinx.android.synthetic.main.activity_tabungan.*

class TabunganActivity : AppCompatActivity(), TabunganPresenter.Listener {

    private lateinit var presenter: TabunganPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabungan)

        presenter = TabunganPresenter(this)

        btnBuatLiburan.setOnClickListener {
            val goToFormTabunganOne = Intent(
                this,
                FormTabunganOneActivity::class.java
            )
            startActivity(goToFormTabunganOne)
        }

        ivBackMainTabungan.setOnClickListener {
            finish()
        }
        presenter.fetchWisataPilihanData()
    }

    override fun showData(adapterWisataPilihan: ListWisataAdapter) {
        val layoutManagerLinear =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvMainTabungan.layoutManager = layoutManagerLinear
        rvMainTabungan.adapter = adapterWisataPilihan
        rvMainTabungan.overScrollMode = View.OVER_SCROLL_NEVER
    }


}