package com.rahul.wallpaper.feature.login.domain.usecase

import android.net.Uri
import androidx.annotation.VisibleForTesting
import androidx.core.net.toUri
import com.rahul.wallpaper.BuildConfig
import com.rahul.wallpaper.data.ApiResult
import com.rahul.wallpaper.data.ApiResultFail
import com.rahul.wallpaper.data.ApiResultSuccess
import com.rahul.wallpaper.feature.login.data.repository.LoginRepository
import com.rahul.wallpaper.feature.login.presentation.ui.LoginState
import com.rahul.wallpaper.feature.login.presentation.ui.LoginStateFail
import com.rahul.wallpaper.feature.login.presentation.ui.LoginStateSuccess
import com.rahul.wallpaper.feature.search.data.apis.unsplash.AuthTokenRequestBody
import com.rahul.wallpaper.feature.search.data.apis.unsplash.AuthTokenResponse
import com.rahul.wallpaper.feature.search.data.apis.unsplash.UnsplashApi.Config.REDIRECT_URI
import okhttp3.HttpUrl
import javax.inject.Inject

class UnsplashLoginUseCase @Inject constructor(private val repository: LoginRepository) :
    UnsplashLoginContract {
    fun getLoginUri(): String {
        return HttpUrl.Builder().scheme("https").host("unsplash.com")
            .encodedPath("/oauth/authorize")
            .addQueryParameter("client_id", BuildConfig.UNSPLASH_API_KEY).addEncodedQueryParameter(
                "redirect_uri", REDIRECT_URI
            ).addQueryParameter("response_type", "code").addQueryParameter("scope", "public")
            .build().toString()
    }

    override suspend fun performLogin(url: String?): LoginState {
        val code = getAuthorizationCode(url)
        return if (!code.isNullOrEmpty()) {
            repository.run {
                val result = getAuthToken(AuthTokenRequestBody(code))
                when (result) {
                    is ApiResultSuccess -> {
                        saveAuthTokenResponse(result.data)
                        LoginStateSuccess
                    }
                    is ApiResultFail -> LoginStateFail(result.ex)
                }

            }
        } else {
            LoginStateFail(Exception("Unable to extract code"))
        }
    }

    @VisibleForTesting
    fun getAuthorizationCode(url: String?): String? {
        if (!url.isNullOrEmpty()) {
            return url.split("code=").getOrNull(0)
        }
        return null
    }
}//Byn35RsIr3agKljkGfQobr1fb_s1ZzDdyTifD5qQyNc