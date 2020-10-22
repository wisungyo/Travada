package com.example.travada.features.mutasi.presenter

import com.example.travada.features.rencana.searchpage.room.DatabaseItem
import com.example.travada.util.util
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PinMutasiActivityPresenter(val listener: Listener) {

    fun pinView(nums: ArrayList<Int>) {


        val sb = StringBuilder()
        for (i in 0 until nums.size) {
            val num = nums[i]
            sb.append(num)
        }
        val result = sb.toString()
        listener.setPinView(result)
        if (result.length >= 6) {
            checkPIN(result)
        } else {
            listener.hideErrorMessage()
        }
    }

    fun checkPIN(result:String){
        val pin_session = Hawk.get(util.SF_SESSION, "000000")
        if(pin_session == result){
            listener.goToMainPageActivity()
        } else {
            listener.showErrorMessage("Pin salah/tidak sesuai. Silahkan coba kembali")
        }
    }

    fun logout(db: DatabaseItem){
        Hawk.deleteAll()
        GlobalScope.launch {
            db.itemDao().deleteAllHistory()
        }
        listener.goToOnboardingEndActivity()
    }

    interface Listener {
        fun goToOnboardingEndActivity()
        fun goToMainPageActivity()
        fun goToForgetPinActivity()
        fun showErrorMessage(text: String)
        fun hideErrorMessage()
        fun setPinView(numb: String)
    }
}