package com.login.presentation.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dispatchers.DispatcherQualifiers
import com.login.presentation.ui.UiState
import com.login.presentation.ui.UiStateInitial
import com.login.presentation.ui.UiStateLoading
import com.login.presentation.ui.UiStateSuccess
import com.login.domain.usecase.CookiesUseCase
import com.login.domain.usecase.UnsplashLoginUseCase
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

    fun processLoginCompleted(uri:Uri){
//        viewModelScope.launch(io) {
//            loginUseCase.processLoginCompleted(uri)
//        }
    }

    fun clearCookies() {
        viewModelScope.launch(io) {
            uiState = UiStateLoading()
            //TODO Rahul fix below
//            cookiesUseCase.clearCookies(AppContract.instance?.provideAppContext())
            uiState = UiStateSuccess()
        }
    }

}