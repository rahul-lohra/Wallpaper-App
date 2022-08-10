package com.rahul.notificationstest.feature.search.data.apis.unsplash

sealed class UnsplashException(message: String) : Exception(message)

class RateLimitReachedException : UnsplashException("Rate Limit Reached")