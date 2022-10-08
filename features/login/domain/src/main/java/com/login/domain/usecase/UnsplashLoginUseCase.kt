package com.login.domain.usecase

import androidx.annotation.VisibleForTesting
import com.data.ApiResultFail
import com.data.ApiResultSuccess
import com.login.data.repository.LoginRepository
import com.login.domain.models.LoginDomainState
import com.login.domain.models.LoginDomainStateFail
import com.login.domain.models.LoginDomainStateSuccess
import com.search.data.BuildConfig
import com.unsplash.AuthTokenRequestBody
import com.unsplash.UnsplashApi.Config.REDIRECT_URI
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

    override suspend fun performLogin(url: String?): LoginDomainState {
        val code = getAuthorizationCode(url)
        return if (!code.isNullOrEmpty()) {
            repository.run {
                val result = getAuthToken(AuthTokenRequestBody(code))
                when (result) {
                    is ApiResultSuccess -> {
                        saveAuthTokenResponse(result.data)
                        LoginDomainStateSuccess
                    }
                    is ApiResultFail -> LoginDomainStateFail(result.ex)
                }

            }
        } else {
            LoginDomainStateFail(Exception("Unable to extract code"))
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