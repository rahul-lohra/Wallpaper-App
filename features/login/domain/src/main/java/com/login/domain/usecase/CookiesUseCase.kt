package com.login.domain.usecase

import android.content.Context
import android.os.Build
import android.webkit.CookieManager
import android.webkit.CookieSyncManager
import timber.log.Timber
import javax.inject.Inject

class CookiesUseCase @Inject constructor() {

    fun clearCookies(context: Context?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null)
            CookieManager.getInstance().flush()
        } else if (context != null) {
            val cookieSyncManager = CookieSyncManager.createInstance(context)
            cookieSyncManager.startSync()
            val cookieManager: CookieManager = CookieManager.getInstance()
            cookieManager.removeAllCookie()
            cookieManager.removeSessionCookie()
            cookieSyncManager.stopSync()
            cookieSyncManager.sync()
        } else {
            Timber.e("Context is null")
        }
    }
}