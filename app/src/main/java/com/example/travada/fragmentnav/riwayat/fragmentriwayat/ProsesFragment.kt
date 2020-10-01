package com.example.travada.fragmentnav.riwayat.fragmentriwayat

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.travada.R
import com.example.travada.detailriwayat.view.DetailRiwayatActivity
import com.example.travada.features.rencana.pojo.GetDestinasiResponse
import com.example.travada.fragmentnav.riwayat.adapter.AdapterRiwayatProses
import com.example.travada.fragmentnav.riwayat.pojo.GetPemesananRiwayatResponse
import kotlinx.android.synthetic.main.fragment_riwayat_item.view.*
import kotlinx.android.synthetic.main.fragment_riwayat_proses.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class ProsesFragment : Fragment(), ProsesFragmentPresenter.Listener, ProsesFragmentPresenter.ListenerAdapter {

    private lateinit var presenter: ProsesFragmentPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_riwayat_proses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ProsesFragmentPresenter(this, this)
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchDataRiwayat()
    }

    override fun showData(list: List<GetPemesananRiwayatResponse.Data>) {
        val adapterRiwayatProses = AdapterRiwayatProses(list, presenter, this)
        val linearLayoutRiwayatProses = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rv_riwayat_proses.adapter = adapterRiwayatProses
        rv_riwayat_proses.layoutManager = linearLayoutRiwayatProses
    }

    override fun showDetailRiwayat(idDestinasi: Int, idPemesanan: Int) {
        val intentDetailRiwayat = Intent(context, DetailRiwayatActivity::class.java)
        intentDetailRiwayat.putExtra("ID_DESTINASI", idDestinasi)
        intentDetailRiwayat.putExtra("ID_PEMESANAN", idPemesanan)
        startActivity(intentDetailRiwayat)
    }

    override fun showDataError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun showData(
        dataInfo: GetDestinasiResponse.Data?,
        dataPemesananRiwayatResponse: GetPemesananRiwayatResponse.Data,
        holder: AdapterRiwayatProses.ViewHolder
    ) {
        // change the img
        if (dataInfo != null) {
            if (dataInfo.gambarList.isNotEmpty()) {
                Glide
                    .with(holder.itemView.context)
                    .load(dataInfo.gambarList[0])
                    .centerCrop()
                    .into(holder.itemView.iv_riwayat_item)
            } else {
                Glide
                    .with(this)
                    .load("https://cdn.thegeekdiary.com/wp-content/plugins/accelerated-mobile-pages/images/SD-default-image.png")
                    .centerCrop()
                    .into(holder.itemView.iv_riwayat_item)
            }

            holder.itemView.tv_riwayat_item_title.text = dataInfo.namaTrip
            holder.itemView.tv_riwayat_item_date.text = "${dataInfo.berangkat} - ${dataInfo.pulang}"
            holder.itemView.tv_riwayat_item_made_date.text = dataInfo.createdAt

            val df = DecimalFormat("#,###")
            df.decimalFormatSymbols = DecimalFormatSymbols(Locale.ITALY)
            holder.itemView.tv_riwayat_item_money.text = "Rp. ${df.format(dataPemesananRiwayatResponse.pemesanan.total)}"

            holder.itemView.tv_riwayat_item_status.text = "Menunggu"
        }
    }

}