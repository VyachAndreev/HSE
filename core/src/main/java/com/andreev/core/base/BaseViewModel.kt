package com.andreev.core.base

import androidx.lifecycle.ViewModel
import com.andreev.core.api_interfaces.API
import com.andreev.core.di.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

abstract class BaseViewModel: ViewModel() {
    @Inject
    lateinit var api: API

    protected val scopeMain = CoroutineScope(Dispatchers.Main)

    fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }
}