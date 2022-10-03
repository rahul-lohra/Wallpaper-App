package com.data.di.modules

import android.content.Context
import com.data.keyvaluedatasource.AppDataStore
import com.data.keyvaluedatasource.KeyValueStorage
import dagger.Module
import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent

@Module
//@InstallIn(SingletonComponent::class)
class StorageModule(val context: Context) {

//    @AppScope
    @Provides
    fun provideAppDataStorage(): KeyValueStorage {
        return AppDataStore(context, "user_pref")
    }
}