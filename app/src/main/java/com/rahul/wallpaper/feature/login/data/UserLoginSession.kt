package com.rahul.wallpaper.feature.login

import android.content.Context
import androidx.annotation.StringDef
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserLoginSession(val context: Context) {

    private object PreferencesKeys {
        val SHOW_COMPLETED = booleanPreferencesKey("show_completed")
    }

    val userToken: String? = null

    suspend fun saveData(key: Preferences.Key<Double>, data: Double) {
//        context.dataStore.edit {
//            it[key] = data
//        }

        saveData(doublePreferencesKey("a"), Double.MIN_VALUE)
    }
}

@StringDef(UserSessionKeys.TOKEN)
@Retention
annotation class UserSessionKeys {
    companion object {
        const val TOKEN = "TOKEN"
    }
}