package com.rahul.wallpaper.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelProviderFactoryImpl @Inject constructor(private val mutableMap: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return mutableMap[modelClass]!!.get() as T
    }
}