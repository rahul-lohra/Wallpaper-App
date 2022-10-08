package com.login.data.repository

import com.search.data.credentials.CredentialsStorage
import com.login.data.datasource.RemoteDataSource
import com.unsplash.AuthTokenRequestBody
import com.unsplash.AuthTokenResponse
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val credentialsStorage: CredentialsStorage
) {

    suspend fun getAuthToken(body: AuthTokenRequestBody) = remoteDataSource.getAuthToken(body)

    suspend fun saveAuthTokenResponse(authTokenResponse: AuthTokenResponse) =
        credentialsStorage.saveCredentials(authTokenResponse)
}