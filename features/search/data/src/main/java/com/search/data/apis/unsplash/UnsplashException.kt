package com.search.data.apis.unsplash

import java.io.IOException

sealed class UnsplashException(message: String) : IOException(message)

class RateLimitReachedException : UnsplashException("Rate Limit Reached")