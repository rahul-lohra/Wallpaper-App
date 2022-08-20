package com.rahul.wallpaper.di.modules

import android.content.Context
import com.rahul.wallpaper.di.scope.AppScope
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