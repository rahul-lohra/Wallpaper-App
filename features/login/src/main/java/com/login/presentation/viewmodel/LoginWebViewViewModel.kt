package com.login.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.di.app.AppContract
import com.dispatchers.DispatcherQualifiers
import com.login.domain.usecase.CookiesUseCase
import com.login.domain.usecase.UnsplashLoginUseCase
import com.login.presentation.UiMapper
import com.login.presentation.ui.UiState
import com.login.presentation.ui.UiStateInitial
import com.login.presentation.ui.UiStateLoading
import com.login.presentation.ui.UiStateSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class LoginWebViewViewModel @Inject constructor(
    @Named(DispatcherQualifiers.IO) private val io: CoroutineDispatcher,
    @Named(DispatcherQualifiers.DEFAULT) private val default: CoroutineDispatcher,
    private val loginUseCase: UnsplashLoginUseCase,
    private val cookiesUseCase: CookiesUseCase,
    private val uiMapper: UiMapper
) :
    ViewModel() {

    var uiState by mutableStateOf<UiState>(UiStateInitial)
        private set

    fun getLoginUrl(): String {
        return loginUseCase.getLoginUri()
    }

    fun processPageFinishedUrl(url: String) {
        viewModelScope.launch(io) {
            val state = loginUseCase.performLogin(url)
            uiState = uiMapper.toUiState(uiState, state)
        }
    }

    fun clearCookies() {
        viewModelScope.launch(io) {
            uiState = UiStateLoading
            cookiesUseCase.clearCookies(AppContract.instance?.provideAppContext())
            uiState = UiStateSuccess(false)
        }
    }
}