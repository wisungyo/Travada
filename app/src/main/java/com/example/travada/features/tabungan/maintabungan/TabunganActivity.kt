package com.example.travada.features.tabungan.maintabungan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.tabungan.adapter.ListWisataAdapter
import com.example.travada.features.tabungan.formtabungansatu.FormTabunganOneActivity
import kotlinx.android.synthetic.main.activity_tabungan.*

class TabunganActivity : AppCompatActivity() {
    private val listWisata = arrayListOf(
        DataWisata("Pantai Ancol", "Rp. 100.000", "1 bulan ", R.drawable.leicester),
        DataWisata("Pantai Ancol", "Rp. 100.000", "1 bulan ", R.drawable.leicester),
        DataWisata("Pantai Ancol", "Rp. 100.000", "1 bulan ", R.drawable.leicester),
        DataWisata("Pantai Ancol", "Rp. 100.000", "1 bulan ", R.drawable.leicester),
        DataWisata("Pantai Ancol", "Rp. 100.000", "1 bulan ", R.drawable.leicester)
    )

    val adapterWisata =
        ListWisataAdapter(
            listWisata
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabungan)

        btnBuatLiburan.setOnClickListener {
            val goToFormTabunganOne = Intent(
                this,
                FormTabunganOneActivity::class.java
            )
            startActivity(goToFormTabunganOne)
        }

        val layoutManagerLinear =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvMainTabungan.layoutManager = layoutManagerLinear
        rvMainTabungan.adapter = adapterWisata
        rvMainTabungan.overScrollMode = View.OVER_SCROLL_NEVER
    }


}