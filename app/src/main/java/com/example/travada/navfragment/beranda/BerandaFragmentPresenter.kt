package com.example.travada.navfragment.beranda

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.features.rencana.network.TPApiClient
import com.example.travada.features.rencana.pojo.GetPopulerResponse
import com.example.travada.features.rencana.pojo.GetTabunganUserAll
import com.example.travada.features.rencana.pojo.GetUserInfo
import com.example.travada.navfragment.beranda.adapter.AdapterBerita
import com.example.travada.navfragment.beranda.adapter.AdapterInformasi
import com.example.travada.navfragment.beranda.adapter.AdapterTabungan
import com.example.travada.navfragment.beranda.adapter.AdapterTrip
import com.example.travada.sampeldata.*
import com.example.travada.util.util
import com.orhanobut.hawk.Hawk
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BerandaFragmentPresenter(val listener: Listener): AppCompatActivity() {

    fun fetchData() {
        listener.showLoadingDialog()
        TPApiClient.TP_API_SERVICES.getUserInfo(Hawk.get(util.SF_TOKEN, "")).enqueue(object : Callback<GetUserInfo> {
            override fun onResponse(call: Call<GetUserInfo>, response: Response<GetUserInfo>) {
                if (!response.isSuccessful) {
                    listener.showDataError("Fetching data gagal")
                    return
                }

                Hawk.put(util.SF_ID, response.body()?.data?.id)
                Hawk.put(util.SF_USERNAME, response.body()?.data?.namaLengkap)

                listener.showSaldo(response.body()?.data?.balance)
            }

            override fun onFailure(call: Call<GetUserInfo>, t: Throwable) {
                listener.showDataError(t.toString())
            }

        })

        TPApiClient.TP_API_SERVICES.getTabunganUserAll(Hawk.get(util.SF_TOKEN, "")).enqueue(object : Callback<GetTabunganUserAll> {
            override fun onResponse(
                call: Call<GetTabunganUserAll>,
                response: Response<GetTabunganUserAll>
            ) {
                if (!response.isSuccessful) {
                    listener.showDataError("Fetching data gagal")
                    return
                }
                response.body()?.data?.let { getAdapterTabungan(it) }
            }

            override fun onFailure(call: Call<GetTabunganUserAll>, t: Throwable) {
                listener.showDataError(t.toString())
                listener.hideLoadingDialog()
            }

        })

        val listTabungan = arrayListOf(
            DataTabungan(R.drawable.tabungan, "Pulau Komodo", 80),
            DataTabungan(R.drawable.tabungan, "Gunung Bromo", 60),
            DataTabungan(R.drawable.tabungan, "Ranu Kumbolo", 30),
            DataTabungan(R.drawable.tabungan, "Pulau Bali",50),
            DataTabungan(R.drawable.tabungan, "Kota Blitar",10)
        )

        val listInformasi = arrayListOf(
            DataInformasi("Wujudkan Impian Liburanmu", "Rencanakan dengan mudah, cepat, dan aman", R.drawable.informasi),
            DataInformasi("Tabung Bareng Teman Lebih Mudah", "Tabungan bersama mudah, dan super aman", R.drawable.ic_main_tabungan)
        )

        TPApiClient.TP_API_SERVICES.getPopulerDestination().enqueue(object : Callback<GetPopulerResponse> {
            override fun onResponse(
                call: Call<GetPopulerResponse>,
                response: Response<GetPopulerResponse>
            ) {
                if (response.isSuccessful && response.body()?.status == "OK") {
                    response.body()?.data?.let { getAdapterTripPopuler(it) }
                } else {
                    listener.showDataError("Fetching data gagal")
                }
                listener.hideLoadingDialog()
            }

            override fun onFailure(call: Call<GetPopulerResponse>, t: Throwable) {
                listener.showDataError(t.toString())
                listener.hideLoadingDialog()
            }
        })

        val listBerita = arrayListOf(
            DataBerita(
                R.drawable.berita_isi_saldo,
                "Isi Saldo Rekening Travada",
                "Ada Cashback Rp 6.500 buat kamu yang isi saldo rekening Travada minimum Rp 500.000 dari Bank Binar",
                "31 Agustus 2020"),
            DataBerita(
                R.drawable.berita_paket_data,
                "Beli Paket Data",
                "Ada Cashback Rp 6.500 buat kamu yang beli paket data ke semua operator",
                "30 Oktober 2020"),
            DataBerita(R.drawable.berita_bayar_pln,
                "Beli Token PLN",
                "Ada Cashback Rp 6.500 buat kamu yang beli token PLN melalui Travada",
                "31 Desember  2020"),
            DataBerita(R.drawable.berita_beli_token,
                "Bayar Tagihan PLN",
                "Ada Cashback Rp 6.500 buat kamu yang bayar tagihan PLN melalui Travada",
                "31 Desember  2020")
        )

//        val adapterTabungan = AdapterTabungan(listTabungan, this)
        val adapterInformasi = AdapterInformasi(listInformasi, this)
        val adapterBerita = AdapterBerita(listBerita, this)
        val linearLayoutTabungan = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val linearLayoutInformasi = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val linearLayoutBerita = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        listener.showData(
//            adapterTabungan,
            adapterInformasi,
            adapterBerita,
            linearLayoutTabungan,
            linearLayoutInformasi,
            linearLayoutBerita
        )
    }

    fun getAdapterTripPopuler(it: List<GetPopulerResponse.Data>) {
        val adapterTrip             = AdapterTrip(it, this)
        val linearLayoutTrip        = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        listener.showTripPopuler(
            adapterTrip,
            linearLayoutTrip
        )
    }

    fun getAdapterTabungan(data: List<GetTabunganUserAll.Data>) {
        val adapterTabungan         = AdapterTabungan(data, this)
        val linearLayoutTabungan    = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        listener.showAdapterTabungan(adapterTabungan, linearLayoutTabungan)
    }

    fun doMutasi() {
        listener.showMutasi()
    }

    fun doTransfer() {
        listener.showTransfer()
    }

    fun doPembelian() {
        listener.showPembelian()
    }

    fun doTopup() {
        listener.showTopup()
    }

    fun doTabungan() {
        listener.showTabungan()
    }

    fun doRencana() {
        listener.showRencana()
    }

    fun doLihatSemuaLiburan() {
        listener.showLihatSemuaLiburan()
    }

    fun doLihatSemuaTrip() {
        listener.showLihatSemuaTrip()
    }

    fun tripItemClicked(id: Int) {
        listener.showTripItemClicked(id)
    }

    fun tabunganItemClicked() {
        listener.showTabunganItemClicked()
    }

    fun informasiItemClicked() {
        listener.showInformasiItemClicked()
    }

    fun goToDetailBerita(itemBerita: DataBerita) {
        listener.showDetailBerita(itemBerita)
    }

    fun doLihatSemuaBerita() {
        listener.showLihatSemuaBerita()
    }

    interface Listener {
        fun showSaldo(balance: Int?)
        fun showData(
//            adapterTabungan: AdapterTabungan,
            adapterInformasi: AdapterInformasi,
            adapterBerita: AdapterBerita,
            linearLayoutTabungan: LinearLayoutManager,
            linearLayoutInformasi: LinearLayoutManager,
            linearLayoutBerita: LinearLayoutManager
        )
        fun showTripPopuler(adapterTrip: AdapterTrip, linearLayoutTrip: LinearLayoutManager)
        fun showMutasi()
        fun showTransfer()
        fun showPembelian()
        fun showTopup()
        fun showTabungan()
        fun showRencana()
        fun showLihatSemuaLiburan()
        fun showLihatSemuaTrip()
        fun showTripItemClicked(id: Int)
        fun showTabunganItemClicked()
        fun showInformasiItemClicked()
        fun showDetailBerita(itemBerita: DataBerita)
        fun showLihatSemuaBerita()
        fun showDataError(error: String)
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun checkLoadingDialog(): Boolean
        fun showAdapterTabungan(
            adapterTabungan: AdapterTabungan,
            linearLayoutTabungan: LinearLayoutManager
        )
    }
}