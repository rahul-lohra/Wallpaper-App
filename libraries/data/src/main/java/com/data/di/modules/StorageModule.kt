package com.data.di.modules

import android.content.Context
import com.data.di.scope.AppDataScope
import com.data.keyvaluedatasource.AppDataStore
import com.data.keyvaluedatasource.KeyValueStorage
import dagger.Module
import dagger.Provides

@Module
class StorageModule(val context: Context) {

    @Provides
    @AppDataScope
    fun provideAppDataStorage(): KeyValueStorage {
        return AppDataStore(context, "user_pref")
    }
}