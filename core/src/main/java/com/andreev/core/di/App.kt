package com.andreev.core.di

import android.app.Application
import com.andreev.core.BuildConfig
import timber.log.Timber


class App: Application() {
    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        appComponent = DaggerApplicationComponent.builder()
            .build()
    }
}