package com.rahul.wallpaper

import android.app.Application
import com.rahul.wallpaper.di.component.AppComponent
import com.rahul.wallpaper.di.component.DaggerAppComponent
import com.rahul.wallpaper.di.modules.AppContextModule
import timber.log.Timber

class App : Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        initLogger()
        initDagger()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.factory()
            .create(AppContextModule(this))
    }
}