package com.data.keyvaluedatasource

import kotlinx.coroutines.flow.Flow

interface CredentialStorage {
    suspend fun saveAccessToken(accessToken: String)
    suspend fun getUserId(): Flow<String>
}