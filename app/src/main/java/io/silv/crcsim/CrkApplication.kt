package io.silv.crcsim

import android.app.Application
import io.silv.crcsim.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CrkApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CrkApplication)
            modules(appModule)
        }
    }
}