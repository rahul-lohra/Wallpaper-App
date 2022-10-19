package com.unsplash.di.modules

import com.data.keyvaluedatasource.CredentialStorage
import com.data.keyvaluedatasource.KeyValueStorage
import com.squareup.moshi.Moshi
import com.unsplash.data.UnsplashCredentialStorage
import com.unsplash.data.UnsplashUserData
import com.unsplash.di.scope.UnSplashScope
import dagger.Module
import dagger.Provides

@Module
class UnsplashStorageModule {

    @Provides
    @UnSplashScope
    fun provideCredentials(
        keyValueStorage: KeyValueStorage,
        moshi: Moshi
    ): CredentialStorage {
        return UnsplashCredentialStorage(
            moshi.adapter(UnsplashUserData::class.java),
            keyValueStorage
        )
    }
    @Provides
    @UnSplashScope
    fun provideUnsplashCredentials(
        keyValueStorage: KeyValueStorage,
        moshi: Moshi
    ): UnsplashCredentialStorage {
        return UnsplashCredentialStorage(
            moshi.adapter(UnsplashUserData::class.java),
            keyValueStorage
        )
    }

}