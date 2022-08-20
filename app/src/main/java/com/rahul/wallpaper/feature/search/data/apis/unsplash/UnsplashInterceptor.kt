package com.rahul.wallpaper.feature.search.data.apis.unsplash

import com.rahul.wallpaper.ResponseCodes
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class UnsplashInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = addRequestHeaders(chain.request())
        val response = chain.proceed(request)

        val rateLimitRemaining = response.headers[UnsplashApi.ResponseHeaders.RATE_LIMIT_REMAINING]
        if (response.code == ResponseCodes.TOO_MANY_REQUESTS && (rateLimitRemaining == null || rateLimitRemaining == "0" )) {
            throw RateLimitReachedException()
        } else {
            return response
        }
    }

    private fun addRequestHeaders(request: Request):Request {
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