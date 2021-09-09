package com.andreev.hse

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.andreev.core.base.BaseActivity
import com.andreev.core.di.ApplicationComponent
import com.andreev.hse.databinding.ActivityMainBinding
import com.andreev.lessons_flow.ui.lessons.LessonsFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getContentViewResId(): Int = R.layout.activity_main

    override fun injectDependencies(applicationComponent: ApplicationComponent) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchFragment(LessonsFragment(), false)
    }

    fun launchFragment(
        fragment: Fragment,
        addToStack: Boolean = true,
        extras: Bundle? = null,
        replace: Boolean = true,
    ) {
        super.launchFragment(R.id.fragment_container, fragment, addToStack, extras, replace)
    }
}