package com.data.keyvaluedatasource

import kotlinx.coroutines.flow.Flow

interface CredentialStorage {
    suspend fun saveAuthTokens(accessToken: String, refreshToken: String)
    suspend fun getUserId(): Flow<String>
    suspend fun getAccessToken(): Flow<String>
    suspend fun getRefreshToken(): Flow<String>
}