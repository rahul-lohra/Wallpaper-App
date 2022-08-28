package com.rahul.wallpaper.feature.login.di

import com.rahul.wallpaper.di.component.AppComponent
import com.rahul.wallpaper.feature.login.ui.activity.LoginWebViewActivity
import com.rahul.wallpaper.feature.login.ui.fragments.LoginWebViewFragment
import dagger.Component

@LoginScope
@Component(
    modules = [LoginViewModelModule::class],
    dependencies = [AppComponent::class]
)
interface LoginComponent {
    fun inject(activity: LoginWebViewActivity)
    fun inject(fragment: LoginWebViewFragment)

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): LoginComponent
    }

}