package com.example.travada.util

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.orhanobut.hawk.Hawk

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Hawk.init(this).build();
        createNotificationChannels()

    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel1 = NotificationChannel(
                util.NOTIF_CHANNEL1,
                " Channel1",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel1.description = "make notification channel 1"

            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel1)
        }

    }

}