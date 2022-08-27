package com.rahul.wallpaper.feature.unsplash.di

import com.rahul.wallpaper.di.component.AppComponent
import com.rahul.wallpaper.feature.credentials.CredentialsStorage
import dagger.Component

@UnsplashScope
@Component(
    modules = [StorageModule::class],
    dependencies = [AppComponent::class]
)
interface UnsplashComponent {

    fun provideCredentials(): CredentialsStorage

//    @Component.Factory
//    interface Factory {
//        fun create(appComponent: AppComponent): UnsplashComponent
//    }
}