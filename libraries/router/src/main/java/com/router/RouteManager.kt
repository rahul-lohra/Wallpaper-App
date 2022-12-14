package com.router

import LogData
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.logger.ServerLogger
import com.logger.data.Priority

class RouteManager private constructor() : AppRouter {

    companion object {
        fun getInstance() = RouteManager()
    }

    fun route(context: Context, pathPattern: String): Boolean {
        if (context !is AppCompatActivity) return false

        val uri = Uri.Builder().scheme(context.getString(R.string.route_internal))
            .authority(context.getString(R.string.route_host)).appendEncodedPath(pathPattern).build()
        val intent = Intent(
            Intent.ACTION_VIEW,
            uri
        )
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.addCategory(Intent.CATEGORY_BROWSABLE)
        intent.resolveActivity(context.packageManager)?.run {
            context.startActivity(intent)
            return true
        }
        ServerLogger.d(Priority.P2, LogData("RouteManager", "$uri not found"))
        return false
    }
}