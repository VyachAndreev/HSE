package com.andreev.lessons_flow.ui.lessons

import com.andreev.core.base.BaseFragment
import com.andreev.lessons_flow.R
import com.andreev.lessons_flow.databinding.FragmentLessonsBinding

class LessonsFragment: BaseFragment<FragmentLessonsBinding, LessonsViewModel>() {
    override fun getLayoutRes(): Int = R.layout.fragment_lessons
}