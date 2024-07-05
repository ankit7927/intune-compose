package com.lmptech.intune

import android.app.Application
import com.lmptech.intune.data.AppContainer
import com.lmptech.intune.data.DefaultAppContainer

class IntuneApplication : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = DefaultAppContainer(this)
    }
}