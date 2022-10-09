package com.unsplash.data

import com.data.keyvaluedatasource.CredentialStorage
import com.data.keyvaluedatasource.KeyValueStorage
import com.unsplash.AuthTokenResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UnsplashCredentialStorage @Inject constructor(private val keyValueStorage: KeyValueStorage) :
    CredentialStorage {
    companion object {
        const val TOKEN = "token"
    }

    suspend fun saveCredentials(authTokenResponse: AuthTokenResponse) {
        keyValueStorage.saveString(TOKEN, authTokenResponse.accessToken)
    }

    override suspend fun saveAccessToken(accessToken: String) {

    }

    override suspend fun getUserId(): Flow<String> {
        return keyValueStorage.getString(TOKEN)
    }
}