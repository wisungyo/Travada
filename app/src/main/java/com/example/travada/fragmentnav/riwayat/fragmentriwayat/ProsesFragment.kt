package com.example.travada.fragmentnav.riwayat.fragmentriwayat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.fragmentnav.riwayat.RiwayatFragmentPresenter
import com.example.travada.fragmentnav.riwayat.adapter.AdapterRiwayatProses
import kotlinx.android.synthetic.main.fragment_riwayat_proses.*

class ProsesFragment : Fragment(), ProsesFragmentPresenter.Listener {

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
        presenter = ProsesFragmentPresenter(this)
        presenter.fetchDataRiwayat()
    }

    override fun showData(
        adapterRiwayatProses: AdapterRiwayatProses,
        linearLayoutRiwayatProses: LinearLayoutManager
    ) {
        rv_riwayat_proses.adapter = adapterRiwayatProses
        rv_riwayat_proses.layoutManager = linearLayoutRiwayatProses
    }

}