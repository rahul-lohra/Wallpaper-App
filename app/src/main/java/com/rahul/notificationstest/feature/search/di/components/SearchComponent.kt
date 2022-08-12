package com.rahul.notificationstest.feature.search.di.components

import com.rahul.notificationstest.di.component.AppComponent
import com.rahul.notificationstest.feature.search.di.modules.SearchInterceptorModule
import com.rahul.notificationstest.feature.search.di.modules.SearchNetworkModule
import com.rahul.notificationstest.feature.search.di.modules.SearchViewModelModule
import com.rahul.notificationstest.feature.search.di.scopes.SearchScope
import com.rahul.notificationstest.feature.search.ui.fragments.SearchFragment
import com.rahul.notificationstest.feature.search.ui.fragments.UnsplashHomeFragment
import dagger.Component

@SearchScope
@Component(
    modules = [
        SearchInterceptorModule::class,
        SearchNetworkModule::class,
        SearchViewModelModule::class,
    ],
    dependencies = [AppComponent::class]
)
interface SearchComponent {

    fun inject(fragment: SearchFragment)
    fun inject(fragment: UnsplashHomeFragment)

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): SearchComponent
    }
}