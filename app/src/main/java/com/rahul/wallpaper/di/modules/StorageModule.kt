package com.rahul.wallpaper.di.modules

import android.content.Context
import com.rahul.wallpaper.data.keyvaluedatasource.AppDataStore
import com.rahul.wallpaper.data.keyvaluedatasource.KeyValueStorage
import com.rahul.wallpaper.di.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module(includes = [AppContextModule::class])
class StorageModule() {

    @AppScope
    @Provides
    fun provideAppDataStorage(@AppScope context: Context): KeyValueStorage {
        return AppDataStore(context, "user_pref")
    }
}