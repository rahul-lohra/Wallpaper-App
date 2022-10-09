package com.search.data.datasource

import com.data.keyvaluedatasource.CredentialStorage
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(val credentialsStorage: CredentialStorage) {

    suspend fun isLoggedIn() = flow<Boolean> {
        credentialsStorage.getUserId().collect {
            emit(it.isNotEmpty())
        }
    }

}