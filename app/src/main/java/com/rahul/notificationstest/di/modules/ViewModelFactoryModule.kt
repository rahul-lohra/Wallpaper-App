package com.rahul.notificationstest.di.modules

import androidx.lifecycle.ViewModelProvider
import com.rahul.notificationstest.di.scope.AppScope
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @AppScope
    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}