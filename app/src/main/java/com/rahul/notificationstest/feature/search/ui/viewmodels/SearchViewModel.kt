package com.rahul.notificationstest.feature.search.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahul.notificationstest.State
import com.rahul.notificationstest.feature.search.domain.usecase.SearchUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var searchUseCase: SearchUseCase

    val flowData: SharedFlow<State<String>> = MutableSharedFlow<State<String>>()

    fun getData(){
        viewModelScope.launch {
            searchUseCase.getData()
        }

    }
}