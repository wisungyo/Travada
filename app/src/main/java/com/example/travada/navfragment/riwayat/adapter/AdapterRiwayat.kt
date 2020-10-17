package com.example.travada.navfragment.riwayat.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.travada.navfragment.riwayat.fragmentriwayat.ProsesFragment
import com.example.travada.navfragment.riwayat.fragmentriwayat.StatusFragment

class AdapterRiwayat (fm: FragmentManager): FragmentPagerAdapter(fm) {

    private val pages = listOf(
        ProsesFragment(),
        StatusFragment()
    )

    override fun getCount(): Int {
        return pages.size
    }

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Proses"
            else -> "Status"
        }
    }
}