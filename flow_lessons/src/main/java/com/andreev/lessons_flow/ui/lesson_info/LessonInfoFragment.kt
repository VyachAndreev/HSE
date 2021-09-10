package com.andreev.lessons_flow.ui.lesson_info

import com.andreev.core.base.BaseFragment
import com.andreev.core.di.ApplicationComponent
import com.andreev.lessons_flow.R
import com.andreev.lessons_flow.databinding.FragmentLessonsBinding

class LessonInfoFragment: BaseFragment<FragmentLessonsBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_lesson_info
    override fun injectDependencies(applicationComponent: ApplicationComponent) {

    }
}