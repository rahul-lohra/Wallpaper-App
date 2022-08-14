package com.rahul.notificationstest.feature.search.ui.fragments

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout
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
) : LinearLayout(context, attrs, 0) {

    var prevY = 0f
    var yOffset = 0f


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
//        val result = super.dispatchTouchEvent(ev)
        handleTouchEvent(ev)
//        Timber.d("dispatchTouchEvent = $result, action = ${ev.getActionFromMotionEvent()}")
        return super.dispatchTouchEvent(ev)
    }

//    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
//        val result = handleTouchEvent(ev)
//        Timber.d("onInterceptTouchEvent = $result, action = ${ev.getActionFromMotionEvent()}")
//        return false
//    }

//    override fun onTouchEvent(ev: MotionEvent): Boolean {
//        val result = handleTouchEvent(ev)
//        Timber.d("onTouchEvent = $result, action = ${ev.getActionFromMotionEvent()}")
//        return false
//    }

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
//        if (result) {
//            requestDisallowInterceptTouchEvent(false)
//            return true
//        } else {
//            requestDisallowInterceptTouchEvent(true)
//            return false
//        }
    }
}