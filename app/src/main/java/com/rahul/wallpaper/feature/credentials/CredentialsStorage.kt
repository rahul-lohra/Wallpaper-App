package com.rahul.wallpaper.feature.credentials

import com.rahul.wallpaper.data.keyvaluedatasource.KeyValueStorage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CredentialsStorage @Inject constructor(private val keyValueStorage: KeyValueStorage) {
    companion object{
        val USER_ID = "userId"
        val TOKEN = "token"
    }

    suspend fun saveCredentials() {
        keyValueStorage.saveString(USER_ID,"Hello")
    }

    suspend fun getUserId(): Flow<String> {
        return keyValueStorage.getString(USER_ID)
    }
}