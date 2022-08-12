package com.rahul.notificationstest.feature.search.data.apis.pixabay

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class PixabayInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}