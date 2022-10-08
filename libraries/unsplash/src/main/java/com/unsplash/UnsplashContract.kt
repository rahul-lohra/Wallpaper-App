package com.unsplash

import com.unsplash.di.component.UnsplashComponent

interface UnsplashContract {
    fun provideUnsplashComponent(): UnsplashComponent
}