package com.rahul.notificationstest.feature.search.data.apis.unsplash

import java.io.IOException

sealed class UnsplashException(message: String) : IOException(message)

class RateLimitReachedException : UnsplashException("Rate Limit Reached")