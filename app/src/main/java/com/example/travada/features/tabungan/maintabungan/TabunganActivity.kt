package com.example.travada.features.tabungan.maintabungan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.tabungan.adapter.ListTabunganAdapter
import com.example.travada.features.tabungan.detailtabungan.view.DetailTabunganActivity
import com.example.travada.features.tabungan.detailtabungan.view.DetailTabunganFragment
import com.example.travada.features.tabungan.formtabungansatu.FormTabunganOneActivity
import com.example.travada.features.tabungan.models.DataWisata
import com.example.travada.features.tabungan.pojo.GetAllTabunganResponse
import com.example.travada.mainpage.MainPageActivity
import com.example.travada.util.loadingdialog.LoadingDialog
import kotlinx.android.synthetic.main.activity_tabungan.*


class TabunganActivity : AppCompatActivity(), TabunganPresenter.Listener {

    private lateinit var presenter: TabunganPresenter
    val MyFragment = LoadingDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabungan)

        presenter = TabunganPresenter(this)

        btnBuatLiburan.setOnClickListener {
            val goToFormTabunganOne = Intent(this, FormTabunganOneActivity::class.java)
            startActivity(goToFormTabunganOne)
        }

        ivBackMainTabungan.setOnClickListener {
            val intent = Intent(this,MainPageActivity::class.java)
            startActivity(intent)
        }
        presenter.dataTabunganList()
    }

    override fun showItemClicked(idTabungan: Int) {
        val intentDetailTabungan = Intent(this, DetailTabunganActivity::class.java)
        intentDetailTabungan.putExtra("TABUNGAN_ID", idTabungan)
        startActivity(intentDetailTabungan)

    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }

    override fun implementAllTabungan(getTabungan: MutableList<GetAllTabunganResponse.Data>) {
        setUpRecyclerView(getTabungan)
    }

    fun setUpRecyclerView(listTabungan : List<GetAllTabunganResponse.Data>){
        Log.d("TRAVASAVE", "id=${listTabungan[0].id}")
        rvMainTabungan.adapter = ListTabunganAdapter(listTabungan, presenter)
        rvMainTabungan.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
    }



    override fun implementAllTabunganFailure(errMessage: String) {
        Toast.makeText(this, "Error : $errMessage", Toast.LENGTH_LONG).show()
    }

    override fun showLoadingDialog() {
        val fm = supportFragmentManager
        MyFragment.isCancelable = false
        MyFragment.show(fm, "Fragment")
    }

    override fun showData(adapterWisataPilihan: ListTabunganAdapter) {
        val layoutManagerLinear =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvMainTabungan.layoutManager = layoutManagerLinear
        rvMainTabungan.adapter = adapterWisataPilihan
        rvMainTabungan.overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun showDetailTabunganWisata(dataWisata: DataWisata) {

        val fragment =
            DetailTabunganFragment()
        val bundle = Bundle()
        bundle.putParcelable("detail", dataWisata)
        fragment.setArguments(bundle)

        getSupportFragmentManager().beginTransaction()
            .add(R.id.frameDetailTabungan, fragment).commit();
    }


}