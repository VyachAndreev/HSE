package com.andreev.core.di.modules

import com.andreev.core.api_interfaces.API
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    private const val baseUrl = "https://api.hseapp.ru/v3/ruz/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesLessonsAPI() = retrofit.create(API::class.java)
}