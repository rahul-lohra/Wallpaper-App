package com.rahul.wallpaper.feature.search.data.datasource

import androidx.compose.runtime.collectAsState
import com.rahul.wallpaper.feature.credentials.CredentialsStorage
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(val credentialsStorage: CredentialsStorage) {

    suspend fun isLoggedIn() = flow<Boolean> {
        credentialsStorage.getUserId().collect {
            emit(it.isNotEmpty())
        }
    }

}