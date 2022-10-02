package com.core

import android.view.View
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

object KeyboardUtils {
    fun View.hideKeyboard(window: Window) =
        WindowCompat.getInsetsController(window,this)
            .hide(WindowInsetsCompat.Type.ime())
}