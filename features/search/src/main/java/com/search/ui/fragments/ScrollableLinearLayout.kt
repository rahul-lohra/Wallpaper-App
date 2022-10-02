package com.search.ui.fragments

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.NestedScrollingParent3
import timber.log.Timber

fun MotionEvent.getActionFromMotionEvent(): String {
    return when (action) {
        MotionEvent.ACTION_DOWN -> "DOWN"
        MotionEvent.ACTION_UP -> "DOWN"
        MotionEvent.ACTION_MOVE -> "MOVE"
        else -> "UNKNOWN"
    }
}

class ScrollableLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs, 0), NestedScrollingParent3 {

    var prevY = 0f
    var yOffset = 0f


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        handleTouchEvent(ev)
        return super.dispatchTouchEvent(ev)
    }

    private fun handleTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    prevY = event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    val tempYOffset = yOffset + (event.rawY - prevY)
                    yOffset = if (tempYOffset >= 0f) {
                        0f
                    } else if (tempYOffset <= -180f) {
                        -180f
                    } else {
                        tempYOffset
                    }
                    y = yOffset
                    prevY = event.rawY
                }
                MotionEvent.ACTION_UP -> {}
            }
        }
        Timber.d("action = ${event?.action}, yOffset = $yOffset")
        val result = yOffset in (-178f..0f)
        return result
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        Timber.d("onStartNestedScroll")
        return super.onStartNestedScroll(child, target, axes)
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        super.onNestedScrollAccepted(child, target, axes)
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        super.onStopNestedScroll(target)
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {

    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {

    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {

    }
}