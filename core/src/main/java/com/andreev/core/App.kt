package com.andreev.core

import android.app.Application
import com.andreev.core.api_interfaces.LessonsAPI
import retrofit2.Retrofit
import timber.log.Timber
import retrofit2.converter.gson.GsonConverterFactory


class App: Application() {
    private var api: LessonsAPI? = null

    override fun onCreate() {
        super.onCreate()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(LessonsAPI::class.java)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private companion object {
        const val baseUrl = "https://api.hseapp.ru/v3/ruz/"
    }
}