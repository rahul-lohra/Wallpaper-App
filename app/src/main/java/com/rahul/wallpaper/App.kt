package com.rahul.wallpaper

import android.app.Application
import android.content.Context
import com.di.app.AppContract
import com.di.app.component.AppComponent
import com.di.app.component.DaggerAppComponent
import com.di.app.modules.AppContextModule
import com.variant.BuildVariant
import com.variant.Variant

import timber.log.Timber

class App : Application(), AppContract {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        AppContract.instance = this
        if (BuildConfig.DEBUG) {
            BuildVariant.setBuildVariant(Variant.DEBUG)
        } else {
            BuildVariant.setBuildVariant(Variant.RELEASE)
        }


        initLogger()
        initDagger()

    }

    private fun initLogger() {
        if (BuildVariant.isDebug()) {
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

    override fun provideAppComponent(): AppComponent {
        return appComponent
    }
}