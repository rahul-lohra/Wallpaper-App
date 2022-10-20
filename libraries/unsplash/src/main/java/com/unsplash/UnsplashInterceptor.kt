package com.unsplash

import com.data.ResponseCodes
import com.data.keyvaluedatasource.CredentialStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class UnsplashInterceptor @Inject constructor(val storage: CredentialStorage) : Interceptor,
    CoroutineScope {

    private var accessToken = ""
    private var refreshToken = ""

    init {
        observeAccessToken()
        observeReserveToken()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Unconfined
    private fun observeAccessToken() {
        launch {
            storage.getAccessToken()
                .collect {
                    accessToken = it
                }
        }
    }
    private fun observeReserveToken() {
        launch {
            storage.getRefreshToken()
                .collect {
                    refreshToken = it
                }
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = addAuthHeader(addRequestHeaders(chain.request()))
        val response = chain.proceed(request)

        val rateLimitRemaining = response.headers[UnsplashApi.ResponseHeaders.RATE_LIMIT_REMAINING]
        if (response.code == ResponseCodes.TOO_MANY_REQUESTS && (rateLimitRemaining == null || rateLimitRemaining == "0")) {
            throw RateLimitReachedException()
        } else {
            return response
        }
    }

    private fun addAuthHeader(request: Request): Request {
            return if (accessToken.isNotEmpty())
                request.newBuilder()
                    .addHeader("Authorization", "Bearer $accessToken")
                    .build()
            else
                request
        }

    private fun addRequestHeaders(request: Request): Request {
        return request.newBuilder()
            .addHeader(
                UnsplashApi.RequestHeaders.AUTHORIZATION,
                UnsplashApi.RequestHeaders.AUTHORIZATION_VALUE
            )
            .addHeader(
                UnsplashApi.RequestHeaders.AcceptVersion.KEY,
                UnsplashApi.RequestHeaders.AcceptVersion.VALUE
            )
            .build()
    }
}