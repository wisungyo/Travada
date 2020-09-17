package com.example.travada.features.rencana.wisnu

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travada.sampeldata.DataCicilanUser

class KonfirmasiRencanaActivityPresenter (val listener: Listener): AppCompatActivity() {
    private lateinit var listUser: ArrayList<DataCicilanUser>

    fun fetchDataCicilan(jumlahOrang: Int, jumlahBiaya: Int) {
        listener.showDataCicilan(jumlahOrang, jumlahBiaya)
        listUser = ArrayList<DataCicilanUser>()
        for (i in 1..jumlahOrang) {
            listUser.add(
                DataCicilanUser(
                    "ABC1230${i}",
                    "Orang $i",
                    "123456${i}",
                    "orang${i}@gmail.com",
                    false
                )
            )
        }
    }

    fun changeCicilanUserStatus(position: Int) {
        listUser[position].status = !listUser[position].status
        fetchDataCicilanLayout()
    }
    
    fun fetchDataCicilanLayout() {
        val adapterKonfirmasiRencana = AdapterKonfirmasiRencanaActivity(listUser, this)
        val linearLayoutKonfirmasiRencana = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        listener.showCicilanList(adapterKonfirmasiRencana, linearLayoutKonfirmasiRencana)
    }

    fun checkNextButtonCondition(jumlahOrang: Int) {
        var condition = true
        for (i in 1..jumlahOrang) {
            if (!listUser[i-1].status) {
                condition = false
            }
        }
        listener.showNextButtonCondition(condition)
    }

    fun nextButtonClicked() {
        listener.showNextButtonClicked("Detail pemesanan sudah benar?")
    }

    fun doBack() {
        listener.showBack()
    }

    /*
        LISTENER
     */
    interface Listener {
        fun showDataCicilan(jumlahOrang: Int, jumlahBiaya: Int)
        fun showCicilanList(
            adapterKonfirmasiRencanaActivity: AdapterKonfirmasiRencanaActivity,
            linearLayoutManager: LinearLayoutManager
        )
        fun showNextButtonCondition(condition: Boolean)
        fun showNextButtonClicked(title: String)
        fun showBack()
    }
}