package com.rahul.notificationstest.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelProviderFactory @Inject constructor(val mutableMap: Map<Class<out ViewModel>, ViewModel>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return mutableMap[modelClass] as T
    }
}