package com.login.data.repository

import com.data.keyvaluedatasource.CredentialStorage
import com.login.data.datasource.RemoteDataSource
import com.unsplash.AuthTokenRequestBody
import com.unsplash.AuthTokenResponse
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val credentialsStorage: CredentialStorage
) {

    suspend fun getAuthToken(body: AuthTokenRequestBody) = remoteDataSource.getAuthToken(body)

    suspend fun saveAuthTokenResponse(authTokenResponse: AuthTokenResponse) =
        credentialsStorage.saveAuthTokens(authTokenResponse.accessToken,authTokenResponse.refreshToken)
}