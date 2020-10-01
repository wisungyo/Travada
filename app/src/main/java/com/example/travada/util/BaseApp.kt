package com.example.travada.util

import android.app.Application
import com.orhanobut.hawk.Hawk

class BaseApp:Application() {

    override fun onCreate() {
        super.onCreate()

        Hawk.init(this).build();
    }
}