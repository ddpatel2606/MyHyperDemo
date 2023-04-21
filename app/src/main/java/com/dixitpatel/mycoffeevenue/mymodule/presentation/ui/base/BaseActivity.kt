package com.dixitpatel.mycoffeevenue.mymodule.presentation.ui.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dixitpatel.mycoffeevenue.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import kotlin.reflect.KSuspendFunction0

abstract class BaseActivity<B : ViewDataBinding>(@LayoutRes private val layoutId: Int) : AppCompatActivity() {

    protected val binding: B by lazy { DataBindingUtil.setContentView<B>(this, layoutId) }

    protected fun collectFlows(collectors: List<KSuspendFunction0<Unit>>) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                collectors.forEach { collector ->
                    launch { collector.invoke() }
                }
            }
        }
    }

    protected fun showSnackBar(message: String, actionText: String? = null, anchor: Boolean = false, action: (() -> Unit)? = null) {
        val view = window.decorView.rootView
        val snackBar = view?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG) }

        if (action != null) snackBar?.setAction(actionText) { action() }
        if (anchor) snackBar?.setAnchorView(R.id.recyclerView)
        snackBar?.show()
    }
}