package com.rahul.notificationstest.feature.search.di.components

import com.rahul.notificationstest.di.component.AppComponent
import com.rahul.notificationstest.di.modules.AppInterceptorsModule
import com.rahul.notificationstest.di.modules.AppNetworkModule
import com.rahul.notificationstest.feature.search.di.modules.SearchInterceptorModule
import com.rahul.notificationstest.feature.search.di.modules.SearchNetworkModule
import com.rahul.notificationstest.feature.search.di.scopes.SearchScope
import com.rahul.notificationstest.feature.search.ui.fragments.SearchFragment
import dagger.BindsInstance
import dagger.Component

@SearchScope
@Component(
    modules = [SearchNetworkModule::class, SearchInterceptorModule::class],
    dependencies = [AppComponent::class]
)
interface SearchComponent {

//    fun inject(fragment: SearchFragment)

//    @Component.Factory
//    interface Factory {
//        fun create(appComponent: AppComponent): SearchComponent
//    }
}