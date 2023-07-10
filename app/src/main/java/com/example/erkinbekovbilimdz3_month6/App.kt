package com.example.erkinbekovbilimdz3_month6

import android.app.Application
import com.example.erkinbekovbilimdz3_month6.core.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(koinModules)
        }
    }
}