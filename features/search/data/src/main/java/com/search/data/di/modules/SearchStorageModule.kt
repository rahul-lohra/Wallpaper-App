package com.search.data.di.modules


import com.data.keyvaluedatasource.CredentialStorage
import com.data.keyvaluedatasource.KeyValueStorage
import com.unsplash.data.UnsplashCredentialStorage
import dagger.Module
import dagger.Provides

@Module
class SearchStorageModule {

    @Provides
    fun provideCredentialStorage(keyValueStorage: KeyValueStorage): CredentialStorage {
        return UnsplashCredentialStorage(keyValueStorage)
    }
}