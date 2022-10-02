package com.search.data.di.modules

import com.search.data.credentials.CredentialsStorage
import com.data.keyvaluedatasource.KeyValueStorage
import dagger.Module
import dagger.Provides

@Module
class SearchStorageModule {

    @Provides
    fun provideCredentialStorage(keyValueStorage: KeyValueStorage): CredentialsStorage {
        return CredentialsStorage(keyValueStorage)
    }
}