package com.example.travada.welcomepage.register.takepicSelfieKTP

import com.example.travada.welcomepage.register.takepicKTP.TakePicKTPPresenter

class TakePicSelfieKTPPresenter(val listener: TakePicSelfieKTPPresenter.Listener) {

    interface Listener {
        fun reviewpic()
        fun takepicture()
        fun saveimage()
    }
}