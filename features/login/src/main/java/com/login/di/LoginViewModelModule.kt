package com.login.di

import androidx.lifecycle.ViewModel
import com.core.di.modules.ViewModelFactoryModule
import com.core.di.modules.ViewModelKey
import com.dispatchers.DispatcherModule
import com.login.presentation.viewmodel.LoginWebViewViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class, DispatcherModule::class])
abstract class LoginViewModelModule {

    @LoginScope
    @Binds
    @IntoMap
    @ViewModelKey(LoginWebViewViewModel::class)
    abstract fun bindSearchViewModel(viewModel: LoginWebViewViewModel): ViewModel
}