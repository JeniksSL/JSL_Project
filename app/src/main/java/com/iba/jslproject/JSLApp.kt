package com.iba.jslproject

import android.app.Application
import timber.log.Timber

class JSLApp: Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())




    }
}