package com.di.app.modules

import android.content.Context
import com.di.app.scope.AppScope
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