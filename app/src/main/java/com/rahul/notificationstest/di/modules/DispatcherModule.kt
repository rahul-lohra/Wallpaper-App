package com.rahul.notificationstest.di.modules

import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

class DispatcherModule {

    @DispatcherQualifiers.Io
    fun provideIO() = Dispatchers.IO

    @DispatcherQualifiers.Main
    fun provideMAIN() = Dispatchers.Main

    @DispatcherQualifiers.Default
    fun provideDefault() = Dispatchers.Default

    class DispatcherQualifiers {
        @Qualifier
        @Retention(AnnotationRetention.RUNTIME)
        annotation class Io

        @Qualifier
        @Retention(AnnotationRetention.RUNTIME)
        annotation class Main

        @Qualifier
        @Retention(AnnotationRetention.RUNTIME)
        annotation class Default
    }
}