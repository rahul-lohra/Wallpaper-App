package com.core.activities

import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.Lifecycle
import com.core.views.BaseRecyclerView
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle

abstract class ScrollingActivity : BaseActivity() {
    abstract fun getRecyclerView(): BaseRecyclerView
    abstract fun performRetryActionFromRecyclerView()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeScrolling()
    }

    private fun observeScrolling() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                getRecyclerView().lastItemVisibleFlow.collect {
                    performRetryActionFromRecyclerView()
                }
            }
        }
    }

    protected fun addListenerToCheckIsLastItemVisibleOnRecyclerView() {
        removeListenerToCheckIsLastItemVisibleOnRecyclerView()
        getRecyclerView().attachScrollListener()
    }

    protected fun removeListenerToCheckIsLastItemVisibleOnRecyclerView() {
        getRecyclerView().removeScrollListener()
    }

    override fun onDestroy() {
        removeListenerToCheckIsLastItemVisibleOnRecyclerView()
        super.onDestroy()
    }
}