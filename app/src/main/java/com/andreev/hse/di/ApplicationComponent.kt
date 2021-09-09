package com.andreev.hse.di

import com.andreev.hse.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
}