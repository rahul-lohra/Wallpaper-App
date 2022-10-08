package com.unsplash.di.component

import com.di.app.component.AppComponent
import com.unsplash.UnsplashApi
import com.unsplash.di.modules.UnsplashNetworkModule
import com.unsplash.di.scope.UnSplashScope
import dagger.Component

@UnSplashScope
@Component(modules = [UnsplashNetworkModule::class], dependencies = [AppComponent::class])

interface UnsplashComponent {
    fun provideUnsplashApi(): UnsplashApi
}