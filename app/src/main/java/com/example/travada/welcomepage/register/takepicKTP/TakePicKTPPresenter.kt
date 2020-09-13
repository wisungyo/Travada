package com.example.travada.welcomepage.register.takepicKTP

class TakePicKTPPresenter(val listener: Listener)  {

    interface Listener {
        fun reviewpic()
        fun takepicture()
        fun saveimage()
    }
}