package com.example.corotinestest.core

import android.app.Application
import com.coroutine.core.di.networkModule
import com.mtek.kidsvid.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()


        startKoin {
            modules(networkModule, viewModelModule)
            androidContext(this@App)
            androidLogger()
        }
    }
}