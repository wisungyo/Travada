package com.example.travada.features.tabungan.maintabungan

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.tabungan.adapter.ListWisataAdapter
import com.example.travada.features.tabungan.detailtabungan.DetailTabunganActivity
import com.example.travada.features.tabungan.detailtabungan.DetailTabunganFragment
import com.example.travada.features.tabungan.formtabungansatu.FormTabunganOneActivity
import com.example.travada.features.tabungan.models.DataWisata
import com.example.travada.mainpage.MainPageActivity
import kotlinx.android.synthetic.main.activity_tabungan.*

class TabunganActivity : AppCompatActivity(), TabunganPresenter.Listener {

    private lateinit var presenter: TabunganPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabungan)

        presenter = TabunganPresenter(this)

        btnBuatLiburan.setOnClickListener {
            val goToFormTabunganOne = Intent(this, FormTabunganOneActivity::class.java)
            goToFormTabunganOne.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(goToFormTabunganOne)
        }

        ivBackMainTabungan.setOnClickListener {
            val intent = Intent(this,MainPageActivity::class.java)
            startActivity(intent)
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

    override fun showDetailTabunganWisata(dataWisata: DataWisata) {

        val fragment = DetailTabunganFragment()
        val bundle = Bundle()
        bundle.putParcelable("detail", dataWisata)
        fragment.setArguments(bundle)

        getSupportFragmentManager().beginTransaction()
            .add(R.id.frameDetailTabungan, fragment).commit();
    }


}