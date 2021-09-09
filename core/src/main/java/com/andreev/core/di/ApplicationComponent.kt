package com.andreev.core.di

import androidx.lifecycle.ViewModel
import com.andreev.core.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    NetworkModule::class,
])
@Singleton
interface ApplicationComponent {
    fun <T: ViewModel>inject(lessonsViewModel: T)
}