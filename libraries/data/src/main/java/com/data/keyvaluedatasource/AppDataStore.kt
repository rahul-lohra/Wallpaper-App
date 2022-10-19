package com.data.keyvaluedatasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

open class AppDataStore constructor(private val context: Context, fileName: String) :
    KeyValueStorage {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = fileName)

    override suspend fun saveString(key: String, data: String) {
        context.dataStore.edit {
            it[stringPreferencesKey(key)] = data
        }
    }

    override suspend fun saveInt(key: String, data: Int) {
        context.dataStore.edit {
            it[intPreferencesKey(key)] = data
        }
    }

    override suspend fun saveDouble(key: String, data: Double) {
        context.dataStore.edit {
            it[doublePreferencesKey(key)] = data
        }
    }

    override suspend fun getString(key: String): Flow<String> {
        return context.dataStore.data.map {
            it[stringPreferencesKey(key)] ?: ""
        }
    }
}