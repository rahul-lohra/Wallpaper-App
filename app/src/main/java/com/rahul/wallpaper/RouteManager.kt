package com.rahul.wallpaper

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri

object RouteManager {
    private val internal_action = Resources.getSystem().getString(R.string.internal_action)
    fun route(context: Context, applink: String): Boolean {
        val intent = Intent(
            internal_action, Uri.Builder().appendPath(applink).build()
        )
        intent.categories.add(Intent.CATEGORY_DEFAULT)
        intent.categories.add(Intent.CATEGORY_BROWSABLE)
        intent.resolveActivity(context.packageManager)?.run {
            context.startActivity(intent)
            return true
        }
        return false
    }
}