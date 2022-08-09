package com.rahul.notificationstest

import android.app.Application
import com.rahul.notificationstest.di.component.AppComponent
import com.rahul.notificationstest.di.component.DaggerAppComponent
import com.rahul.notificationstest.di.modules.AppContextModule

class App : Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.factory()
            .create(AppContextModule(this))
    }
}