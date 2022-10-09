package com.core.views

import android.content.Context
import android.util.AttributeSet
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.core.activities.BaseActivity
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class BaseRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    val lastItemVisibleFlow = MutableSharedFlow<Unit>()

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                if(adapter!=null && layoutManager is LinearLayoutManager){
                    val isLastItemVisible = (layoutManager as LinearLayoutManager)
                        .findLastVisibleItemPosition() == adapter!!.itemCount - 1
                    if (isLastItemVisible) {
                        (context as BaseActivity).lifecycleScope.launch {
                            lastItemVisibleFlow.emit(Unit)
                        }
                    }
                }
            }
        }
    }

    fun attachScrollListener(){
        this.addOnScrollListener(scrollListener)
    }

    fun removeScrollListener(){
        this.removeOnScrollListener(scrollListener)
    }
}