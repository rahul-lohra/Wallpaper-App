package com.rahul.wallpaper.feature.search.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.rahul.wallpaper.di.modules.DispatcherQualifiers
import com.rahul.wallpaper.feature.search.domain.usecase.FollowDomainData
import com.rahul.wallpaper.feature.search.domain.usecase.SearchUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class SearchViewModel @Inject constructor(
    @Named(DispatcherQualifiers.IO) private val io: CoroutineDispatcher,
    @Named(DispatcherQualifiers.DEFAULT) private val default: CoroutineDispatcher,
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    val followersFlow: Flow<FollowDomainData> = searchUseCase.getFollowersData()
    val photosFlow: Flow<PagingData<String>> = searchUseCase.getPagingData()

    fun getData() {
        viewModelScope.launch {
            searchUseCase.getData()
        }
    }

    fun getPagingData() {
        viewModelScope.launch(io) {
        }
    }

}