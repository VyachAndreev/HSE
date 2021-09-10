package com.andreev.core.base

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.andreev.core.R
import com.andreev.core.di.ApplicationComponent

import timber.log.Timber

abstract class BaseActivity<T: ViewDataBinding>: AppCompatActivity() {
    protected val fm by lazy { supportFragmentManager }
    protected lateinit var binding: T
    @IdRes
    protected open val containerId: Int = R.id.fragment_container
    private fun getContentViewResId(): Int = R.layout.activity_main

    protected abstract fun injectDependencies(applicationComponent: ApplicationComponent)

    private fun inflateContentView() {
        binding = DataBindingUtil.setContentView(this, getContentViewResId())
        Timber.i("binding: $binding")
    }

    fun launchFragment(
        @IdRes containerId: Int = R.id.fragment_container,
        fragment: Fragment,
        addToStack: Boolean,
        extras: Bundle? = null,
        replace: Boolean = false,
    ) {
        val ft = fm.beginTransaction()
        fragment.arguments = extras

        if (replace)
            ft.replace(containerId, fragment, fragment.javaClass.simpleName)
        else
            ft.add(containerId, fragment, fragment.javaClass.simpleName)
        if (addToStack) {
            ft.addToBackStack(fragment.javaClass.simpleName)
        }
        ft.commitAllowingStateLoss()
    }

    fun hideSoftKeyboard() {
        Timber.i("hide keyboard")
        currentFocus?.let { view ->
            val imm: InputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showToast(@StringRes text: Int) {
        val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateContentView()
    }
}