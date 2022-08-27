package com.rahul.wallpaper.di.modules

import androidx.annotation.StringDef
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Qualifier

@Module
class DispatcherModule {

    @Named(DispatcherQualifiers.IO)
    @Provides
    fun provideIO(): CoroutineDispatcher = Dispatchers.IO

    @Named(DispatcherQualifiers.MAIN)
    @Provides
    fun provideMAIN(): CoroutineDispatcher = Dispatchers.Main

    @Named(DispatcherQualifiers.DEFAULT)
    @Provides
    fun provideDefault(): CoroutineDispatcher = Dispatchers.Default
}

@StringDef(DispatcherQualifiers.IO, DispatcherQualifiers.MAIN, DispatcherQualifiers.DEFAULT)
@Retention
annotation class DispatcherQualifiers {
    companion object {
        const val IO = "IO"
        const val MAIN = "MAIN"
        const val DEFAULT = "DEFAULT"
    }
}