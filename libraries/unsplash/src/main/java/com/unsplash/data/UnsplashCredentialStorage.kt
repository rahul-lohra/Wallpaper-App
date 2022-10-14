package com.unsplash.data

import com.data.keyvaluedatasource.CredentialStorage
import com.data.keyvaluedatasource.KeyValueStorage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UnsplashCredentialStorage @Inject constructor(private val keyValueStorage: KeyValueStorage) :
    CredentialStorage {
    companion object {
        const val ACCESS_TOKEN = "access_token"
        const val REFRESH_TOKEN = "refresh_token"
    }

    override suspend fun saveAuthTokens(accessToken: String, refreshToken: String) {
        keyValueStorage.saveString(ACCESS_TOKEN, accessToken)
        keyValueStorage.saveString(REFRESH_TOKEN, refreshToken)
    }

    override suspend fun getUserId(): Flow<String> {
        return keyValueStorage.getString("TOKEN")
    }
}