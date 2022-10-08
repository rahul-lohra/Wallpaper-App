package com.unsplash

import java.io.IOException

sealed class UnsplashException(message: String) : IOException(message)

class RateLimitReachedException : UnsplashException("Rate Limit Reached")