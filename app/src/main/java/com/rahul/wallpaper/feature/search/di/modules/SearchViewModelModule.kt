package com.rahul.wallpaper.feature.search.di.modules

import androidx.lifecycle.ViewModel
import com.rahul.wallpaper.feature.search.di.scopes.SearchScope
import com.rahul.wallpaper.feature.search.ui.viewmodels.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class SearchViewModelModule {

    @SearchScope
    @Binds
    @IntoMap
    @ClassKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}