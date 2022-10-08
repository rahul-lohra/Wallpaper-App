package com.rahul.wallpaper

import android.app.Application
import android.content.Context
import com.di.app.AppContract
import com.di.app.component.AppComponent
import com.di.app.component.DaggerAppComponent
import com.di.app.modules.AppContextModule
import com.unsplash.UnsplashContract
import com.unsplash.di.component.DaggerUnsplashComponent
import com.unsplash.di.component.UnsplashComponent
import com.variant.BuildVariant
import com.variant.Variant
import timber.log.Timber

class App : Application(), AppContract, UnsplashContract {

    lateinit var appComponent: AppComponent
    lateinit var unsplashComponent: UnsplashComponent

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
                override fun log(priority: Int, message: String?, vararg args: Any?) {
                    super.log(priority, message, *args)
                }

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

    override fun provideUnsplashComponent(): UnsplashComponent {
        if (!this::unsplashComponent.isInitialized) {
            unsplashComponent = DaggerUnsplashComponent.builder().appComponent(appComponent).build()
        }
        return unsplashComponent
    }
}