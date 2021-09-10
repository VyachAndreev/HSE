package com.andreev.core.base

import androidx.lifecycle.ViewModel
import com.andreev.core.di.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BaseViewModel: ViewModel() {
    protected val scopeIO = CoroutineScope(Dispatchers.IO)

    abstract fun injectDependencies(applicationComponent: ApplicationComponent)
}