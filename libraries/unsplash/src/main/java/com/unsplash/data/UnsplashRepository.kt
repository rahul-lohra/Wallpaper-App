package com.unsplash.data

import com.data.ApiResult
import com.data.repository.BaseRepository
import com.unsplash.UnsplashApi
import javax.inject.Inject

class UnsplashRepository @Inject constructor(private val unsplashApi: UnsplashApi) :
    BaseRepository {
    suspend fun getUserData(userName: String? = null): ApiResult<UnsplashUserData> {
        if (userName.isNullOrEmpty()) {
            return invoke { unsplashApi.getUserData() }
        } else {
            return invoke { unsplashApi.getUserData(userName) }
        }
    }
}