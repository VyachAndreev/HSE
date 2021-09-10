package com.andreev.lessons_flow.ui.lessons

import androidx.lifecycle.MutableLiveData
import com.andreev.core.base.BaseViewModel
import com.andreev.data.models.Lesson
import com.andreev.lessons_flow.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class LessonsViewModel: BaseViewModel() {

    val lessons = MutableLiveData<Array<Lesson>>()

    fun getLessons() {
            scopeMain.launch {
                try {
                    lessons.value = withContext(Dispatchers.IO) { api.getLessons() }
                } catch (e: retrofit2.HttpException) {
                    Timber.e(e)
                    errorMessage.value =  when(e.code()) {
                        400 -> R.string.invalid_input
                        else -> R.string.something_went_wrong
                    }
                }
            }
    }
}