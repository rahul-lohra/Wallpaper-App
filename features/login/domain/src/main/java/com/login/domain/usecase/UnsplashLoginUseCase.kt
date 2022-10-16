package com.login.domain.usecase

import androidx.annotation.VisibleForTesting
import com.data.*
import com.login.data.repository.LoginRepository
import com.login.domain.models.LoginDomainState
import com.login.domain.models.LoginDomainStateFail
import com.login.domain.models.LoginDomainStateSuccess
import com.unsplash.AuthTokenRequestBody
import com.unsplash.UnsplashApi
import com.unsplash.UnsplashApi.Config.REDIRECT_URI
import com.unsplash.domain.UnsplashUserUseCase
import okhttp3.HttpUrl
import javax.inject.Inject

class UnsplashLoginUseCase @Inject constructor(
    private val repository: LoginRepository,
    private val unsplashUserUseCase: UnsplashUserUseCase
) :
    UnsplashLoginContract {
    fun getLoginUri(): String {
        return HttpUrl.Builder().scheme("https").host("unsplash.com")
            .encodedPath("/oauth/authorize")
            .addQueryParameter("client_id", UnsplashApi.Config.ADDRESS_KEY)
            .addEncodedQueryParameter(
                "redirect_uri", REDIRECT_URI
            ).addQueryParameter("response_type", "code")
            .addQueryParameter("scope", "public read_photos read_collections")
            .build().toString()
    }

    override suspend fun performLogin(url: String): LoginDomainState {
        val code = getAuthorizationCode(url)
        return if (!code.isNullOrEmpty()) {
            repository.run {
                val result = getAuthToken(AuthTokenRequestBody(code))
                when (result) {
                    is ApiResultSuccess -> {
                        saveAuthTokenResponse(result.data)
                        when (val selfUserData = unsplashUserUseCase.getUserData(null)) {
                            is ApiResultSuccess -> {
                                saveUserData(selfUserData.data)
                                LoginDomainStateSuccess
                            }
                            is ApiResultFail -> {
                                LoginDomainStateFail(selfUserData.ex)
                            }
                        }
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
            return url.split("code=").getOrNull(1)
        }
        return null
    }
}