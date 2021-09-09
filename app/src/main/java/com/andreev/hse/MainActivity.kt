package com.andreev.hse

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
//    override fun getContentViewResId(): Int = R.layout.activity_main
//
//    override fun injectDependencies(applicationComponent: ApplicationComponent) {
//
//    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
    }
//
//    fun launchFragment(
//        fragment: Fragment,
//        addToStack: Boolean = true,
//        extras: Bundle? = null,
//        replace: Boolean = true,
//    ) {
//        super.launchFragment(R.id.fragment_container, fragment, addToStack, extras, replace)
//    }
}