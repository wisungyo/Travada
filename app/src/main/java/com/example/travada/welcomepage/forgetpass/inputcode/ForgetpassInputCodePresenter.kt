package com.example.travada.welcomepage.forgetpass.inputcode

import android.os.Bundle
import android.os.CountDownTimer

class ForgetpassInputCodePresenter(val listener:Listener) {


    fun startTimer() {
        lateinit var countdown_timer: CountDownTimer
        var time = 60000L

        countdown_timer = object : CountDownTimer(time, 1000) {
            override fun onFinish() {
                listener.hideCountdown()
            }

            override fun onTick(p0: Long) {
                val seconds = (p0 / 1000) % 60
                listener.countdownTimerUI(seconds)
            }
        }
        countdown_timer.start()
    }


    interface Listener {
        fun btnActive()
        fun btnInactive()
        fun ShowErrorMessage()
        fun hideErrorMessage()
        fun goToNextPage(bundle: Bundle)
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showToast(text: String)
        fun showCountdown()
        fun hideCountdown()
        fun countdownTimerUI(count: Long)
    }
}