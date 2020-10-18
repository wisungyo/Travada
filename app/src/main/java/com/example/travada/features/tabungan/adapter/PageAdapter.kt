package com.example.travada.features.tabungan.adapter


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.travada.features.tabungan.detailtabungan.view.DetailTabunganFragment
import com.example.travada.features.tabungan.detailtabungan.view.TransaksiTabunganFragment


class PageAdapter(fm: FragmentManager, val id: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment
        when (position) {
            0 -> {
                fragment = DetailTabunganFragment()
                val bundle = Bundle()
                bundle.putInt("ID_DETAILTABUNGAN", id)
                fragment.setArguments(bundle)
            }
            else ->  fragment = TransaksiTabunganFragment()
        }
        return fragment
    }

    override fun getCount(): Int = 2
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Detail"
            else -> "Transaksi"
        }
    }
}

