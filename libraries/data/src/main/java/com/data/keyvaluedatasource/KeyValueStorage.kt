package com.data.keyvaluedatasource

import kotlinx.coroutines.flow.Flow

interface KeyValueStorage {
    suspend fun saveString(key: String, data: String)
    suspend fun saveInt(key: String, data: Int)
    suspend fun saveDouble(key: String, data: Double)

    suspend fun getString(key: String): Flow<String>
}