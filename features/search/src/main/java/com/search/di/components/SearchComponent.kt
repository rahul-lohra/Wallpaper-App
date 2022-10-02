package com.search.di.components

import android.content.Context
import com.data.di.modules.StorageModule
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
//    dependencies = [AppComponent::class]
)
interface SearchComponent {

    fun inject(fragment: SearchFragment)
    fun inject(fragment: UnsplashHomeFragment)

//    @Component.Factory
//    interface Factory {
//        fun create(context: Context): SearchComponent
//    }
}