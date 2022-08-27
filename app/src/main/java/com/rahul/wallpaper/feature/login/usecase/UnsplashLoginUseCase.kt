package com.rahul.wallpaper.feature.login.usecase

import android.net.Uri
import com.rahul.wallpaper.BuildConfig
import com.rahul.wallpaper.feature.credentials.CredentialsStorage
import javax.inject.Inject

class UnsplashLoginUseCase @Inject constructor() : UnsplashLoginContract {
    fun getLoginUri(): Uri {
        val loginPath = "https://unsplash.com/oauth/authorize"
        return Uri.Builder()
            .appendPath(loginPath)
            .appendQueryParameter("client_id", BuildConfig.UNSPLASH_API_KEY)
            .appendQueryParameter(
                "redirect_uri",
                "https://mywordpressinstall.com/unsplash_callback"
            )
            .appendQueryParameter("response_type", "code")
            .appendQueryParameter("scope", "public")
            .build()
    }

    override suspend fun performLogin() {
        //Do nothing
    }
}