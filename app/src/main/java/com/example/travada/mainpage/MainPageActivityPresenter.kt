package com.example.travada.mainpage

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.travada.R
import com.example.travada.fragmentnav.akun.AkunFragment
import com.example.travada.fragmentnav.beranda.BerandaFragment
import com.example.travada.fragmentnav.notifikasi.view.NotifikasiFragment
import com.example.travada.fragmentnav.riwayat.main.RiwayatFragment

class MainPageActivityPresenter (val listener: Listener): AppCompatActivity() {

    // bottom navigation fragments
    val berandaFragment = BerandaFragment()
    val riwayatFragment = RiwayatFragment()
    val notifikasiFragment =
        NotifikasiFragment()
    val akunFragment = AkunFragment()

    fun setFragment(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.bn_beranda -> {
                listener.showFragment(berandaFragment)
                return true
            }
            R.id.bn_riwayat -> {
                listener.showFragment(riwayatFragment)
                return true
            }
            R.id.bn_notifikasi -> {
                listener.showFragment(notifikasiFragment)
                return true
            }
            R.id.bn_akun -> {
                listener.showFragment(akunFragment)
                return true
            }
            else -> return false
        }
    }

    interface Listener {
        fun showFragment(fragment: Fragment)
    }
}