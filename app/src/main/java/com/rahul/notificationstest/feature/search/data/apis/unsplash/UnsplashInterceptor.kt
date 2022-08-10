package com.rahul.notificationstest.feature.search.data.apis.unsplash

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class UnsplashInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        addRequestHeaders(request)
        val response = chain.proceed(request)

        val rateLimitRemaining = response.headers[UnsplashApi.ResponseHeaders.RATE_LIMIT_REMAINING]
        if (rateLimitRemaining == null || rateLimitRemaining == "0") {
            throw RateLimitReachedException()
        } else {
            return response
        }
    }

    private fun addRequestHeaders(request: Request) {
        request.headers.newBuilder()
            .add(
                UnsplashApi.RequestHeaders.AUTHORIZATION,
                UnsplashApi.RequestHeaders.AUTHORIZATION_VALUE
            )
            .add(
                UnsplashApi.RequestHeaders.AcceptVersion.KEY,
                UnsplashApi.RequestHeaders.AcceptVersion.VALUE
            )
            .build()
    }
}