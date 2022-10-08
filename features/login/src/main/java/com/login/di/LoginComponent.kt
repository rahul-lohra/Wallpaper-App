package com.login.di

import com.data.di.modules.StorageModule
import com.di.app.component.AppComponent
import com.login.presentation.ui.fragments.LoginWebViewFragment
import com.unsplash.di.component.UnsplashComponent
import com.unsplash.di.modules.UnsplashNetworkModule
import dagger.Component

@LoginScope
@Component(
    modules = [LoginViewModelModule::class, StorageModule::class], //TODO Rahul use assisted inject to include objects from search network module and remote unsplash api from Search network module.
    dependencies = [AppComponent::class, UnsplashComponent::class]
)
interface LoginComponent {
    fun inject(fragment: LoginWebViewFragment)
}