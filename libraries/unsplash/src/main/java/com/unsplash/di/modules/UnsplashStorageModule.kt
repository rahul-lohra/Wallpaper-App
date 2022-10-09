package com.unsplash.di.modules

import com.data.keyvaluedatasource.CredentialStorage
import com.data.keyvaluedatasource.KeyValueStorage
import com.unsplash.data.UnsplashCredentialStorage
import dagger.Module
import dagger.Provides

@Module
class UnsplashStorageModule {

    @Provides
    fun provideCredentials(keyValueStorage: KeyValueStorage): CredentialStorage {
        return UnsplashCredentialStorage(keyValueStorage)
    }
}