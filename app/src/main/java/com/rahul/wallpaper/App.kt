package com.rahul.wallpaper

import android.app.Application
import android.content.Context
import com.rahul.wallpaper.di.component.AppComponent
import com.rahul.wallpaper.di.component.DaggerAppComponent
import com.rahul.wallpaper.di.modules.AppContextModule
import timber.log.Timber

class App : Application(), AppContract {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        AppContract.instance = this

        initLogger()
        initDagger()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun isLoggable(tag: String?, priority: Int): Boolean {
                    if (tag?.contains("UnsplashHomeFragment") == true) return false
                    return super.isLoggable(tag, priority)
                }
            })
        }
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.factory()
            .create(AppContextModule(this))
    }

    override fun provideAppContext(): Context {
        return this
    }
}