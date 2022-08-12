package com.rahul.notificationstest.feature.search.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.rahul.notificationstest.State
import com.rahul.notificationstest.feature.search.domain.usecase.SearchUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(val searchUseCase: SearchUseCase) : ViewModel() {

    val flowData: SharedFlow<State<String>> = MutableSharedFlow<State<String>>()
    val photosFlow: Flow<PagingData<String>> = searchUseCase.getPagingData()

    fun getData() {
        viewModelScope.launch {
            searchUseCase.getData()
        }
    }

    fun getPagingData() {
        viewModelScope.launch {

        }
    }
}