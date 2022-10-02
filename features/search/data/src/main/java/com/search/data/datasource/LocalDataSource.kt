package com.search.data.datasource

import com.search.data.credentials.CredentialsStorage
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(val credentialsStorage: CredentialsStorage) {

    suspend fun isLoggedIn() = flow<Boolean> {
        credentialsStorage.getUserId().collect {
            emit(it.isNotEmpty())
        }
    }

}