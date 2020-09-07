package com.example.travada.features.tabungan.detail_tabungan

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PageAdapter (fm: FragmentManager): FragmentPagerAdapter(fm) {

    private val pages = listOf(
        DetailTabunganFragment(),
        TransaksiTabunganFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Detail"
            else -> "Transaksi"
        }
    }
}