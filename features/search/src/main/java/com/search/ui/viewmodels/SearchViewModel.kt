package com.search.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.dispatchers.DispatcherQualifiers
import com.search.domain.FollowDomainData
import com.search.domain.SearchUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class SearchViewModel @Inject constructor(
    @Named(DispatcherQualifiers.IO) private val io: CoroutineDispatcher,
    @Named(DispatcherQualifiers.DEFAULT) private val default: CoroutineDispatcher,
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    //TODO Rahul fix below line
    val followersFlow: Flow<FollowDomainData> = flowOf()
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