package com.di.app

import android.content.Context
import com.di.app.component.AppComponent

interface AppContract {

    fun provideAppContext(): Context
    fun provideAppComponent(): AppComponent


    companion object {
        var instance: AppContract? = null
    }
}