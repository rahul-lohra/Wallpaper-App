package com.rahul.wallpaper.feature.unsplash.di

import com.rahul.wallpaper.data.keyvaluedatasource.KeyValueStorage
import com.rahul.wallpaper.feature.credentials.CredentialsStorage
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @UnsplashScope
    @Provides
    fun provideCredentialStorage(keyValueStorage: KeyValueStorage): CredentialsStorage {
        return CredentialsStorage(keyValueStorage)
    }
}