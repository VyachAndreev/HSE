package com.andreev.hse

import android.app.Application
import com.andreev.network.API
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class App: Application() {
    private var api: API? = null

    override fun onCreate() {
        super.onCreate()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(API::class.java)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private companion object {
        const val baseUrl = "https://api.hseapp.ru/v3/ruz/"
    }
}