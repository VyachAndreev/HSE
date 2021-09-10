package com.andreev.lessons_flow.ui.lessons

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.andreev.core.base.BaseFragment
import com.andreev.core.di.ApplicationComponent
import com.andreev.lessons_flow.R
import com.andreev.lessons_flow.databinding.FragmentLessonsBinding

class LessonsFragment: BaseFragment<FragmentLessonsBinding>() {
    private lateinit var viewModel: LessonsViewModel

    override fun getLayoutRes(): Int = R.layout.fragment_lessons

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLessons()
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        viewModel = ViewModelProvider(this).get(LessonsViewModel::class.java)
        viewModel.injectDependencies(applicationComponent)
    }
}