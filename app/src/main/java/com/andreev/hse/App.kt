package com.andreev.hse

import android.app.Application
import com.andreev.core.BuildConfig
import com.andreev.core.di.ApplicationComponent
import timber.log.Timber


class App: Application() {
    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}