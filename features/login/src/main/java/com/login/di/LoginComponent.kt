package com.login.di

import com.data.di.component.AppDataComponent
import com.di.app.component.AppComponent
import com.login.presentation.ui.fragments.LoginWebViewFragment
import com.unsplash.di.component.UnsplashComponent
import dagger.Component

@LoginScope
@Component(
    modules = [LoginViewModelModule::class], //TODO Rahul use assisted inject to include objects from search network module and remote unsplash api from Search network module.
    dependencies = [AppComponent::class, UnsplashComponent::class, AppDataComponent::class]
)
interface LoginComponent {
    fun inject(fragment: LoginWebViewFragment)
}