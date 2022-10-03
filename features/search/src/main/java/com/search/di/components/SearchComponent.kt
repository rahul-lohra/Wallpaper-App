package com.search.di.components

import com.data.di.modules.StorageModule
import com.di.app.component.AppComponent
import com.search.data.di.modules.SearchNetworkModule
import com.search.di.modules.SearchViewModelModule
import com.search.di.scopes.SearchScope
import com.search.ui.fragments.SearchFragment
import com.search.ui.fragments.UnsplashHomeFragment
import dagger.Component

@SearchScope
@Component(
    modules = [
        SearchNetworkModule::class,
        SearchViewModelModule::class,
        StorageModule::class
    ],
    dependencies = [AppComponent::class]
)
interface SearchComponent {

    fun inject(fragment: SearchFragment)
    fun inject(fragment: UnsplashHomeFragment)

//    @Component.Builder
//    interface Builder {
//        fun context(context: Context): Builder
//        fun dependencies(appComponent: AppComponent): Builder
//        fun build(): SearchComponent
//    }
}