package com.data.di.component

import com.data.di.modules.StorageModule
import com.data.di.scope.AppDataScope
import com.data.keyvaluedatasource.KeyValueStorage
import dagger.Component

@AppDataScope
@Component(modules = [StorageModule::class])
interface AppDataComponent {
    fun keyValueStorage(): KeyValueStorage
}

interface AppDataContract {
    fun provideAppDataComponent(): AppDataComponent
}