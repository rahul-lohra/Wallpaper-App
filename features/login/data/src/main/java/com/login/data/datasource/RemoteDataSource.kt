package com.login.data.datasource

import com.data.ApiResult
import com.data.ApiResultFail
import com.data.ApiResultSuccess
import com.search.data.apis.unsplash.AuthTokenRequestBody
import com.search.data.apis.unsplash.AuthTokenResponse
import com.search.data.apis.unsplash.UnsplashApi
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