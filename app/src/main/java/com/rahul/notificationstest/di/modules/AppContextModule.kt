package com.rahul.notificationstest.di.modules

import android.content.Context
import com.rahul.notificationstest.di.scope.AppScope
import dagger.Binds
import dagger.Module

@Module
interface AppContextModule {

    @AppScope
    @Binds
    fun provideContext(context: Context): Context
}