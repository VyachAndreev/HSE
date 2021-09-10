package com.andreev.core.di

import com.andreev.core.base.BaseViewModel
import com.andreev.core.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    NetworkModule::class
])
@Singleton
interface ApplicationComponent {
    fun inject(viewModel: BaseViewModel)
}