package com.cadi.vane

import android.app.Application
import com.cadi.vane.dimodules.habitModule
import com.cadi.vane.dimodules.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class VaneApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@VaneApplication)
            modules(networkModule, habitModule)
        }
    }
}