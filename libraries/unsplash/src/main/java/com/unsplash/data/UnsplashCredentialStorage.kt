package com.unsplash.data

import com.data.keyvaluedatasource.CredentialStorage
import com.data.keyvaluedatasource.KeyValueStorage
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class UnsplashCredentialStorage (
    private val moshiAdapter: JsonAdapter<UnsplashUserData>,
    private val keyValueStorage: KeyValueStorage
) :
    CredentialStorage {

    internal object Keys {
        const val ACCESS_TOKEN = "access_token"
        const val REFRESH_TOKEN = "refresh_token"
        const val USER_ID = "user_id"
        const val USER_DATA = "user_data"
    }

    companion object {
        var userData: UnsplashUserData? = null
    }

    init {
        prepareStorage()
    }

    private fun prepareStorage() {
        GlobalScope.launch(Dispatchers.IO) {
            val userDataString = keyValueStorage.getString(Keys.USER_DATA).firstOrNull()
            if (!userDataString.isNullOrEmpty()) {
                userData = moshiAdapter.fromJson(userDataString)
            }
        }
    }

    override suspend fun saveAuthTokens(accessToken: String, refreshToken: String) {
        keyValueStorage.saveString(Keys.ACCESS_TOKEN, accessToken)
        keyValueStorage.saveString(Keys.REFRESH_TOKEN, refreshToken)
    }

    suspend fun saveUserData(unsplashUserData: UnsplashUserData) {
        keyValueStorage.saveString(Keys.USER_DATA, moshiAdapter.toJson(unsplashUserData))
        keyValueStorage.saveString(Keys.USER_ID, unsplashUserData.id)
        userData = unsplashUserData
    }

    override suspend fun getUserId(): Flow<String> {
        return keyValueStorage.getString(Keys.USER_ID)
    }
}