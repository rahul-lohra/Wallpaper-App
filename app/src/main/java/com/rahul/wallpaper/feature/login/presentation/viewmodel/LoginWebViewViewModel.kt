package com.rahul.wallpaper.feature.login.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahul.wallpaper.AppContract
import com.rahul.wallpaper.di.modules.DispatcherQualifiers
import com.rahul.wallpaper.feature.login.presentation.ui.UiState
import com.rahul.wallpaper.feature.login.presentation.ui.UiStateInitial
import com.rahul.wallpaper.feature.login.presentation.ui.UiStateLoading
import com.rahul.wallpaper.feature.login.presentation.ui.UiStateSuccess
import com.rahul.wallpaper.feature.login.domain.usecase.CookiesUseCase
import com.rahul.wallpaper.feature.login.domain.usecase.UnsplashLoginUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class LoginWebViewViewModel @Inject constructor(
    @Named(DispatcherQualifiers.IO) private val io: CoroutineDispatcher,
    private val loginUseCase: UnsplashLoginUseCase,
    private val cookiesUseCase: CookiesUseCase
) :
    ViewModel() {

    var uiState by mutableStateOf<UiState>(UiStateInitial())
    private set

    fun getLoginUrl(): String {
        return loginUseCase.getLoginUri()
    }

    fun processPageFinishedUrl(url:String?){
        viewModelScope.launch(io) {
            loginUseCase.performLogin(url)
        }
    }

    fun clearCookies() {
        viewModelScope.launch(io) {
            uiState = UiStateLoading()
            cookiesUseCase.clearCookies(AppContract.instance?.provideAppContext())
            uiState = UiStateSuccess()
        }
    }

}