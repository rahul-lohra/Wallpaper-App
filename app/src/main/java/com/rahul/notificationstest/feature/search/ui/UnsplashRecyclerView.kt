package com.rahul.notificationstest.feature.search.ui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import com.rahul.notificationstest.feature.search.ui.fragments.getActionFromMotionEvent
import timber.log.Timber

class UnsplashRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        Timber.d("onTouchEvent action = ${ev.getActionFromMotionEvent()}")
        return super.onTouchEvent(ev)
    }
}