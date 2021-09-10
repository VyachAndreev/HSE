package com.andreev.hse

import android.os.Bundle
import com.andreev.core.base.BaseActivity
import com.andreev.core.databinding.ActivityMainBinding
import com.andreev.core.di.ApplicationComponent
import com.andreev.lessons_flow.ui.lessons.LessonsFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun injectDependencies(applicationComponent: ApplicationComponent) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (fm.backStackEntryCount == 0) {
            launchFragment(fragment = LessonsFragment(), replace = false, addToStack = false)
        }
    }
}