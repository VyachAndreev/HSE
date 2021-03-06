package com.andreev.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.andreev.core.R
import com.andreev.core.di.App
import com.andreev.core.di.ApplicationComponent

abstract class BaseFragment<T: ViewDataBinding>: Fragment() {
    protected lateinit var binding: T
    protected val gone by lazy { View.GONE }
    protected val visible by lazy { View.VISIBLE }

    private fun inflateView(inflater: LayoutInflater) {
        binding = DataBindingUtil.inflate(
            inflater,
            getLayoutRes(),
            null,
            false,
        )
        binding.lifecycleOwner = this
    }

    protected abstract fun getLayoutRes(): Int

    abstract fun injectDependencies(applicationComponent: ApplicationComponent)

    protected fun launchFragment(
        @IdRes containerId: Int = R.id.fragment_container,
        fragment: Fragment,
        addToStack: Boolean,
        extras: Bundle? = null,
        replace: Boolean = false
    ) {
        (activity as? BaseActivity<*>)?.launchFragment(
            containerId,
            fragment,
            addToStack,
            extras,
            replace,
        )
    }

    protected fun onBackPressed() {
        activity?.onBackPressed()
    }

    protected fun hideSoftKeyboard() {
        (activity as? BaseActivity<*>)?.hideSoftKeyboard()
    }

    protected fun showToast(@StringRes text: Int) {
        (activity as? BaseActivity<*>)?.showToast(text)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as? App)?.let {
            injectDependencies(it.appComponent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflateView(inflater)
        return binding.root
    }

    protected open val errorMessageObserver = Observer<@StringRes Int> {
        showToast(it)
    }
}