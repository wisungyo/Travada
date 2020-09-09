package com.example.travada.fragmentnav.riwayat.fragmentriwayat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.R
import com.example.travada.fragmentnav.riwayat.adapter.AdapterRiwayatStatus
import kotlinx.android.synthetic.main.fragment_riwayat_status.*

class StatusFragment : Fragment(), StatusFragmentPresenter.Listener {

    private lateinit var presenter: StatusFragmentPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_riwayat_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = StatusFragmentPresenter(this)
        presenter.fetchDataRiwayat()
    }

    override fun showData(
        adapterRiwayatStatus: AdapterRiwayatStatus,
        linearLayoutRiwayatStatus: LinearLayoutManager
    ) {
        rv_riwayat_status.adapter = adapterRiwayatStatus
        rv_riwayat_status.layoutManager = linearLayoutRiwayatStatus
    }


}