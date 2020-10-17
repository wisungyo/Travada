package com.example.travada.navfragment.beranda

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.berita.view.BeritaActivity
import com.example.travada.berita.view.DetailBeritaActivity
import com.example.travada.features.mutasi.view.MutasiActivity
import com.example.travada.features.rencana.detailrencana.view.DetailRencanaActivity
import com.example.travada.features.rencana.searchpage.TPSearchPageActivity
import com.example.travada.features.rencana.main.RencanaActivity
import com.example.travada.features.tabungan.maintabungan.TabunganActivity
import com.example.travada.navfragment.beranda.adapter.AdapterBerita
import com.example.travada.navfragment.beranda.adapter.AdapterInformasi
import com.example.travada.navfragment.beranda.adapter.AdapterTabungan
import com.example.travada.navfragment.beranda.adapter.AdapterTrip
import com.example.travada.sampeldata.DataBerita
import com.example.travada.util.loadingdialog.LoadingDialog
import com.example.travada.util.util
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_beranda.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class BerandaFragment : Fragment(), BerandaFragmentPresenter.Listener {
    private lateinit var presenter: BerandaFragmentPresenter
    val MyFragment = LoadingDialog()
    object status {
        var statusSaldo = false
    }

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
        iv_mainpage_card_eye.setOnClickListener {
           if (status.statusSaldo) {
               status.statusSaldo = false
               et_mainpage_card_saldo_jumlah.transformationMethod =
                   HideReturnsTransformationMethod.getInstance()
               iv_mainpage_card_eye.setBackgroundResource(R.drawable.ic_eye)
           } else {
               status.statusSaldo = true
               et_mainpage_card_saldo_jumlah.transformationMethod =
                   PasswordTransformationMethod.getInstance()
               iv_mainpage_card_eye.setBackgroundResource(R.drawable.ic_eye_blind)
           }
        }

        iv_mainpage_card_topup.setOnClickListener { presenter.doTopup() }
        iv_mainpage_card_travasave.setOnClickListener { presenter.doTabungan() }
        iv_mainpage_card_travaplan.setOnClickListener { presenter.doRencana() }
        iv_mainpage_card_transfer.setOnClickListener { presenter.doTransfer() }
        iv_mainpage_card_mutasi.setOnClickListener { presenter.doMutasi() }
        iv_mainpage_card_pembelian.setOnClickListener { presenter.doPembelian() }

        tv_mainpage_trip_lihat_semua_liburan_kamu.setOnClickListener {
            presenter.doLihatSemuaLiburan()
        }

        tv_mainpage_trip_lihat_semua_trip_pilihan.setOnClickListener {
            presenter.doLihatSemuaTrip()
        }

        tv_mainpage_berita_lihat_semua.setOnClickListener {
            presenter.doLihatSemuaBerita()
        }
    }

    override fun showSaldo(balance: Int?) {
        val df = DecimalFormat("#,###")
        df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)

        balance?.let { et_mainpage_card_saldo_jumlah.setText(df.format(it)) }
        tv_mainpage_username.text = Hawk.get(util.SF_USERNAME, "")
    }

    override fun showData(
//        adapterTabungan: AdapterTabungan,
        adapterInformasi: AdapterInformasi,
        adapterBerita: AdapterBerita,
        linearLayoutTabungan: LinearLayoutManager,
        linearLayoutInformasi: LinearLayoutManager,
        linearLayoutBerita: LinearLayoutManager
    ) {

//        rv_mainpage_tabungan.adapter = adapterTabungan
        rv_mainpage_informasi.adapter = adapterInformasi
        rv_mainpage_berita.adapter = adapterBerita

//        rv_mainpage_tabungan.layoutManager = linearLayoutTabungan
        rv_mainpage_informasi.layoutManager = linearLayoutInformasi
        rv_mainpage_berita.layoutManager = linearLayoutBerita
    }

    override fun showTripPopuler(adapterTrip: AdapterTrip, linearLayoutTrip: LinearLayoutManager) {
        rv_mainpage_trip.adapter = adapterTrip
        rv_mainpage_trip.layoutManager = linearLayoutTrip
    }

    override fun showMutasi() {
        val intentMutasi = Intent(context, MutasiActivity::class.java)
        startActivity(intentMutasi)
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

    override fun showTopup() {
        Toast.makeText(
            context,
            "Topup under construction..",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showTabungan() {
        val goToNextActivity = Intent(context, TabunganActivity::class.java)
        startActivity(goToNextActivity)
    }

    override fun showRencana() {
        val intentRencana = Intent(context, RencanaActivity::class.java)
        startActivity(intentRencana)
    }

    override fun showLihatSemuaLiburan() {
        val goToNextActivity = Intent(context, TabunganActivity::class.java)
        startActivity(goToNextActivity)
    }

    override fun showLihatSemuaTrip() {
        val intentToSemuaTrip = Intent(context, TPSearchPageActivity::class.java)
        startActivity(intentToSemuaTrip)
    }

    override fun showTripItemClicked(id: Int) {
        val intentItemClicked = Intent(context, DetailRencanaActivity::class.java)
        intentItemClicked.putExtra("DESTINASI_ID", id)
        startActivity(intentItemClicked)
    }

    override fun showTabunganItemClicked() {
        Toast.makeText(
            context,
            "Under construction..",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showInformasiItemClicked() {
        Toast.makeText(
            context,
            "Under construction..",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showDetailBerita(itemBerita: DataBerita) {
        val intentToDetailBerita = Intent(context, DetailBeritaActivity::class.java)
        intentToDetailBerita.putExtra("DATA", itemBerita)
        startActivity(intentToDetailBerita)
    }

    override fun showLihatSemuaBerita() {
        val intentSemuaBerita = Intent(context, BeritaActivity::class.java)
        startActivity(intentSemuaBerita)
    }

    override fun showDataError(error: String) {
        Toast.makeText(
            context,
            error,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showLoadingDialog() {
        val fm=fragmentManager
        MyFragment.isCancelable = false
        fm?.let { MyFragment.show(it, "Fragment") }
    }

    override fun hideLoadingDialog() {
        MyFragment.dismiss()
    }

    override fun checkLoadingDialog(): Boolean {
        return MyFragment.isAdded && MyFragment.isVisible && MyFragment.userVisibleHint
    }

    override fun showAdapterTabungan(
        adapterTabungan: AdapterTabungan,
        linearLayoutTabungan: LinearLayoutManager
    ) {

        rv_mainpage_tabungan.adapter = adapterTabungan
        rv_mainpage_tabungan.layoutManager = linearLayoutTabungan
    }
}