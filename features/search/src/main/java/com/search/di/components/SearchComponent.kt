package com.search.di.components

import com.data.di.component.AppDataComponent
import com.di.app.component.AppComponent
import com.search.di.modules.SearchViewModelModule
import com.search.di.scopes.SearchScope
import com.search.ui.fragments.SearchFragment
import com.search.ui.fragments.UnsplashHomeFragment
import com.unsplash.di.component.UnsplashComponent
import dagger.Component

@SearchScope
@Component(
    modules = [
        SearchViewModelModule::class,
    ],
    dependencies = [AppComponent::class, UnsplashComponent::class, AppDataComponent::class]
)
interface SearchComponent {

    fun inject(fragment: SearchFragment)
    fun inject(fragment: UnsplashHomeFragment)
}