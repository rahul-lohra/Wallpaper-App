package com.rahul.wallpaper.feature.credentials

import com.rahul.wallpaper.data.keyvaluedatasource.KeyValueStorage
import com.rahul.wallpaper.feature.search.data.apis.unsplash.AuthTokenResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CredentialsStorage @Inject constructor(private val keyValueStorage: KeyValueStorage) {
    companion object {
        const val TOKEN = "token"
    }

    suspend fun saveCredentials(authTokenResponse: AuthTokenResponse) {
        keyValueStorage.saveString(TOKEN, authTokenResponse.accessToken)
    }

    suspend fun getUserId(): Flow<String> {
        return keyValueStorage.getString(TOKEN)
    }
}