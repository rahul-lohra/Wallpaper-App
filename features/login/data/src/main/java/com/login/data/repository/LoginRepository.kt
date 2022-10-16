package com.login.data.repository

import com.login.data.datasource.RemoteDataSource
import com.unsplash.AuthTokenRequestBody
import com.unsplash.AuthTokenResponse
import com.unsplash.data.UnsplashCredentialStorage
import com.unsplash.data.UnsplashUserData
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val credentialsStorage: UnsplashCredentialStorage
) {

    suspend fun getAuthToken(body: AuthTokenRequestBody) = remoteDataSource.getAuthToken(body)

    suspend fun saveAuthTokenResponse(authTokenResponse: AuthTokenResponse) =
        credentialsStorage.saveAuthTokens(authTokenResponse.accessToken,authTokenResponse.refreshToken)

    suspend fun saveUserData(unsplashUserData: UnsplashUserData) =
        credentialsStorage.saveUserData(unsplashUserData)

}