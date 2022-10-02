package com.router

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity

class RouteManager private constructor() : AppRouter {

    companion object {
        fun getInstance() = RouteManager()
    }

    fun route(context: Context, pathPattern: String): Boolean {
        if (context !is AppCompatActivity) return false

        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.Builder().scheme(context.getString(R.string.route_internal))
                .authority(context.getString(R.string.route_host)).appendPath(pathPattern).build()
        )
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addCategory(Intent.CATEGORY_BROWSABLE)
        intent.resolveActivity(context.packageManager)?.run {
            context.startActivity(intent)
            return true
        }
        return false
    }
}