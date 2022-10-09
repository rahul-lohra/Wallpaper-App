package com.search.data.apis.pexels

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class PexelsInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}