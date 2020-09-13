package com.example.travada.fragmentnav.riwayat

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.travada.R
import com.example.travada.fragmentnav.riwayat.adapter.AdapterRiwayat
import kotlinx.android.synthetic.main.fragment_riwayat.*

class RiwayatFragment : Fragment(), RiwayatFragmentPresenter.Listener {

    private lateinit var presenter: RiwayatFragmentPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_riwayat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = RiwayatFragmentPresenter()
        vp_riwayat.adapter = AdapterRiwayat(childFragmentManager)
        tl_riwayat.setupWithViewPager(vp_riwayat)
        tl_riwayat.setSelectedTabIndicatorColor(Color.parseColor("#2A85F4"))

    }
}