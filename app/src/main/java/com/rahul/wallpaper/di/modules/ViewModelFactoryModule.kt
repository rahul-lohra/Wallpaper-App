package com.rahul.wallpaper.di.modules

import androidx.lifecycle.ViewModelProvider
import com.rahul.wallpaper.di.scope.AppScope
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @AppScope
    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}