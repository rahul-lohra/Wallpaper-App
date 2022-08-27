package com.rahul.wallpaper.feature.login.viewmodel

import androidx.lifecycle.ViewModel
import com.rahul.wallpaper.feature.login.usecase.UnsplashLoginUseCase
import javax.inject.Inject

class LoginWebViewViewModel @Inject constructor(val unsplashLoginUseCase: UnsplashLoginUseCase) :
    ViewModel() {

    fun getLoginUrl() = unsplashLoginUseCase.getLoginUri().toString()
}