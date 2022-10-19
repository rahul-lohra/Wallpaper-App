package com.unsplash.di.component

import com.data.di.component.AppDataComponent
import com.data.keyvaluedatasource.CredentialStorage
import com.di.app.component.AppComponent
import com.unsplash.UnsplashApi
import com.unsplash.data.UnsplashCredentialStorage
import com.unsplash.di.modules.UnsplashNetworkModule
import com.unsplash.di.modules.UnsplashStorageModule
import com.unsplash.di.scope.UnSplashScope
import dagger.Component

@UnSplashScope
@Component(
    modules = [
        UnsplashNetworkModule::class,
        UnsplashStorageModule::class,
    ],
    dependencies = [AppComponent::class, AppDataComponent::class]
)

interface UnsplashComponent {
    fun provideUnsplashApi(): UnsplashApi
    fun provideCredentialStorage(): CredentialStorage
    fun provideUnsplashCredentialStorage(): UnsplashCredentialStorage
}