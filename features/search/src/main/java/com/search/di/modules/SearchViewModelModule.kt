package com.search.di.modules

import androidx.lifecycle.ViewModel
import com.core.di.modules.ViewModelFactoryModule
import com.core.di.modules.ViewModelKey
import com.dispatchers.DispatcherModule
import com.search.di.scopes.SearchScope
import com.search.ui.viewmodels.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class, DispatcherModule::class])
abstract class SearchViewModelModule {

    @SearchScope
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}