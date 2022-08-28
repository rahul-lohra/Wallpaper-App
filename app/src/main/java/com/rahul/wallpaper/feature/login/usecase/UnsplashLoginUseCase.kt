package com.rahul.wallpaper.feature.login.usecase

import android.net.Uri
import com.rahul.wallpaper.BuildConfig
import okhttp3.HttpUrl
import javax.inject.Inject

class UnsplashLoginUseCase @Inject constructor() : UnsplashLoginContract {
    fun getLoginUri(): String {
        val loginPath = "https://unsplash.com/oauth/authorize"
        val redirectUrl = "https://mywordpressinstall.com/unsplash_callback"
        return HttpUrl.Builder()
            .scheme("https")
            .host("unsplash.com")
            .encodedPath("/oauth/authorize")
            .addQueryParameter("client_id", BuildConfig.UNSPLASH_API_KEY)
            .addEncodedQueryParameter(
                "redirect_uri",
                redirectUrl
            )
            .addQueryParameter("response_type", "code")
            .addQueryParameter("scope", "public")
            .build().toString()
    }

    override suspend fun performLogin() {
        //Do nothing
    }
}