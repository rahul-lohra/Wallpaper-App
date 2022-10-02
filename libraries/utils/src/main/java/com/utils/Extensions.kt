package com.utils

import android.content.res.Resources
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Float.toDp(): Float = this / Resources.getSystem().displayMetrics.density

fun Float.toPx(): Float = this * Resources.getSystem().displayMetrics.density

fun Modifier.onNoRippleClick(onClick: () -> Unit) = composed {
    clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) {
        onClick.invoke()
    }
}