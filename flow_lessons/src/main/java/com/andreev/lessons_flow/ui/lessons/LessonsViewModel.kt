package com.andreev.lessons_flow.ui.lessons

import androidx.lifecycle.MutableLiveData
import com.andreev.core.api_interfaces.LessonsAPI
import com.andreev.core.base.BaseViewModel
import com.andreev.core.di.ApplicationComponent
import kotlinx.coroutines.launch
import javax.inject.Inject

class LessonsViewModel: BaseViewModel() {
    @Inject
    lateinit var lessonsAPI: LessonsAPI

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.inject(this)
    }

    fun getLessons() {
        scopeMain.launch {

        }
    }
}