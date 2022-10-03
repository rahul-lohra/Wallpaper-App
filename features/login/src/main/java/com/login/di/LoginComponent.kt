package com.login.di

import com.data.di.modules.StorageModule
import com.login.presentation.ui.activity.LoginWebViewActivity
import com.login.presentation.ui.fragments.LoginWebViewFragment
import com.search.data.di.modules.SearchNetworkModule
import dagger.Component

@LoginScope
//@Component(
//    modules = [LoginViewModelModule::class, StorageModule::class, SearchNetworkModule::class], //TODO Rahul use assisted inject to include objects from search network module and remote unsplash api from Search network module.
//    dependencies = [AppComponent::class]
//)
interface LoginComponent {
//    fun inject(activity: LoginWebViewActivity)
    fun inject(fragment: LoginWebViewFragment)

//    @Component.Factory
//    interface Factory {
//        fun create(appComponent: AppComponent): LoginComponent
//    }

}