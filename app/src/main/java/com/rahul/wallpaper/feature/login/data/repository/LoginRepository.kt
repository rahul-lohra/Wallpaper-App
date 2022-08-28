package com.rahul.wallpaper.feature.login.data.repository

import com.rahul.wallpaper.feature.credentials.CredentialsStorage
import com.rahul.wallpaper.feature.login.data.datasource.RemoteDataSource
import com.rahul.wallpaper.feature.search.data.apis.unsplash.AuthTokenRequestBody
import com.rahul.wallpaper.feature.search.data.apis.unsplash.AuthTokenResponse
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val credentialsStorage: CredentialsStorage
) {

    suspend fun getAuthToken(body: AuthTokenRequestBody) = remoteDataSource.getAuthToken(body)

    suspend fun saveAuthTokenResponse(authTokenResponse: AuthTokenResponse) =
        credentialsStorage.saveCredentials(authTokenResponse)
}