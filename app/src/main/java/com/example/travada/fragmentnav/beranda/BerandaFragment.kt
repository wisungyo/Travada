package com.example.travada.fragmentnav.beranda

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.berita.BeritaActivity
import com.example.travada.berita.DetailBeritaActivity
import com.example.travada.features.tabungan.maintabungan.TabunganActivity
import com.example.travada.fragmentnav.beranda.adapter.AdapterBerita
import com.example.travada.fragmentnav.beranda.adapter.AdapterInformasi
import com.example.travada.fragmentnav.beranda.adapter.AdapterTabungan
import com.example.travada.fragmentnav.beranda.adapter.AdapterTrip
import com.example.travada.mainpage.MainPageActivity
import com.example.travada.sampeldata.DataBerita
import com.example.travada.sampeldata.DataUser
import kotlinx.android.synthetic.main.fragment_beranda.*
import java.text.DecimalFormat
import java.text.NumberFormat

class BerandaFragment : Fragment(), BerandaFragmentPresenter.Listener {
    private lateinit var presenter: BerandaFragmentPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = BerandaFragmentPresenter(this)

        // fetch the data
        presenter.fetchData()

        // card button listeners
        iv_mainpage_card_mutasi.setOnClickListener { presenter.doMutasi() }
        iv_mainpage_card_transfer.setOnClickListener { presenter.doTransfer() }
        iv_mainpage_card_pembelian.setOnClickListener { presenter.doPembelian() }
        iv_mainpage_card_ewallet.setOnClickListener { presenter.doEwallet() }
        iv_mainpage_card_tabungan.setOnClickListener {
            //TODO gimmic buat besok senen
            val goToNextActivity = Intent(context, TabunganActivity::class.java)
            startActivity(goToNextActivity)
        }
        iv_mainpage_card_rencana.setOnClickListener { presenter.doRencana() }

        // lihat semua berita
        tv_mainpage_berita_lihat_semua.setOnClickListener {
            val intentSemuaBerita = Intent(context, BeritaActivity::class.java)
            startActivity(intentSemuaBerita)
        }
    }

    override fun showData(
        dataUser: DataUser,
        adapterTabungan: AdapterTabungan,
        adapterInformasi: AdapterInformasi,
        adapterTrip: AdapterTrip,
        adapterBerita: AdapterBerita,
        linearLayoutTabungan: LinearLayoutManager,
        linearLayoutInformasi: LinearLayoutManager,
        linearLayoutTrip: LinearLayoutManager,
        linearLayoutBerita: LinearLayoutManager
    ) {
        val formatter: NumberFormat = DecimalFormat("#,###")
        val formattedSaldo: String = formatter.format(dataUser.saldo)
        tv_mainpage_username.text = dataUser.name
        tv_mainpage_card_saldo.text = "Rp. $formattedSaldo"

        rv_mainpage_tabungan.adapter = adapterTabungan
        rv_mainpage_informasi.adapter = adapterInformasi
        rv_mainpage_trip.adapter = adapterTrip
        rv_mainpage_berita.adapter = adapterBerita

        rv_mainpage_informasi.layoutManager = linearLayoutInformasi
        rv_mainpage_trip.layoutManager = linearLayoutTrip
        rv_mainpage_tabungan.layoutManager = linearLayoutTabungan
        rv_mainpage_berita.layoutManager = linearLayoutBerita
    }

    override fun showMutasi() {
        Toast.makeText(
            context,
            "Mutasi under construction..",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showTransfer() {
        Toast.makeText(
            context,
            "Transfer under construction..",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showPembelian() {
        Toast.makeText(
            context,
            "Pembelian under construction..",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showEwallet() {
        Toast.makeText(
            context,
            "eWallet under construction..",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showTabungan() {
//        Toast.makeText(
//            context,
//            "Tabungan under construction..",
//            Toast.LENGTH_SHORT
//        ).show()
    }

    override fun showRencana() {
        Toast.makeText(
            context,
            "Rencana under construction..",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showDetailBerita(itemBerita: DataBerita) {
        val intentToDetailBerita = Intent(context, DetailBeritaActivity::class.java)
        intentToDetailBerita.putExtra("DATA", itemBerita)
        startActivity(intentToDetailBerita)
    }
}