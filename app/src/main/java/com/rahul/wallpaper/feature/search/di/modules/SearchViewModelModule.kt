package com.rahul.wallpaper.feature.search.di.modules

import androidx.lifecycle.ViewModel
import com.rahul.wallpaper.di.modules.ViewModelFactoryModule
import com.rahul.wallpaper.di.modules.ViewModelKey
import com.rahul.wallpaper.feature.search.di.scopes.SearchScope
import com.rahul.wallpaper.feature.search.ui.viewmodels.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
abstract class SearchViewModelModule {

    @SearchScope
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}