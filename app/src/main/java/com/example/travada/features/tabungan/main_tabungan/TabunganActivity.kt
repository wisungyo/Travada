package com.example.travada.features.tabungan.main_tabungan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.tabungan.form_one.FormTabunganActivityOne
import kotlinx.android.synthetic.main.activity_tabungan.*
import kotlinx.android.synthetic.main.list_liburan_pilihan.*

class TabunganActivity : AppCompatActivity(),TabunganPresenter.Listener {

    private lateinit var presenter: TabunganPresenter
    private lateinit var dataWisata: DataListWisata

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabungan)

        btnBuatLiburan.setOnClickListener {
            val goToFormTabunganOne = Intent(this,
                FormTabunganActivityOne::class.java)
            startActivity(goToFormTabunganOne)
        }

        fetchOverviewData()
        presenter.fetchCicilanData()

        presenter= TabunganPresenter(this)
        intent.getParcelableExtra<DataListWisata>("TABUNGAN")?.let{
            dataWisata = it
        }


    }

    private fun fetchOverviewData() {
        tvNamaWisata.text = dataWisata.namaWisata
        imagePerson.setBackgroundResource(dataWisata.gambar)
        tvBiayaWisata.text = dataWisata.biaya
        tvTempo.text = dataWisata.tempo
    }

    override fun showData(
        adapterWisataActivityActivity: ListWisataAdapter,
        linearLayoutWisataActivity: LinearLayoutManager
    ) {
        recylerView.adapter = adapterWisataActivityActivity
        recylerView.layoutManager = linearLayoutWisataActivity
    }
}