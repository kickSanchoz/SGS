package ru.sanchozgamesstore.android

import android.app.Application
import android.content.res.Resources
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        res = resources

//        registerReceiver(object: BroadcastReceiver() {
//            override fun onReceive(context: Context?, intent: Intent?) {
//
//            }
//        }, IntentFilter(BROADCAST_ACTION_UNAUTHORIZED))
    }

    companion object {
        lateinit var res: Resources
    }
}