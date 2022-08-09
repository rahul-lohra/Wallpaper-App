package com.rahul.notificationstest.di.modules

import android.content.Context
import com.rahul.notificationstest.di.scope.AppScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class AppContextModule(val context: Context) {

    @AppScope
    @Provides
    fun providesContext(): Context {
        return context
    }
}