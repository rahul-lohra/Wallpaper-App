package com.rahul.wallpaper.feature.login.data.datasource

import com.rahul.wallpaper.data.ApiResult
import com.rahul.wallpaper.data.ApiResultFail
import com.rahul.wallpaper.data.ApiResultSuccess
import com.rahul.wallpaper.feature.search.data.apis.unsplash.AuthTokenRequestBody
import com.rahul.wallpaper.feature.search.data.apis.unsplash.AuthTokenResponse
import com.rahul.wallpaper.feature.search.data.apis.unsplash.UnsplashApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val unsplashApi: UnsplashApi) {

    suspend fun getAuthToken(body: AuthTokenRequestBody): ApiResult<AuthTokenResponse> {
        return try {
            ApiResultSuccess(unsplashApi.getAuthToken(body))
        } catch (ex: Exception) {
            ApiResultFail(ex)
        }
    }

}