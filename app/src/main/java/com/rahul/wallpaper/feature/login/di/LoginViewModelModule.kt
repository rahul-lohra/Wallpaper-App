package com.rahul.wallpaper.feature.login.di

import androidx.lifecycle.ViewModel
import com.rahul.wallpaper.di.modules.ViewModelFactoryModule
import com.rahul.wallpaper.di.modules.ViewModelKey
import com.rahul.wallpaper.feature.login.viewmodel.LoginWebViewViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
abstract class LoginViewModelModule {

    @LoginScope
    @Binds
    @IntoMap
    @ViewModelKey(LoginWebViewViewModel::class)
    abstract fun bindSearchViewModel(viewModel: LoginWebViewViewModel): ViewModel
}