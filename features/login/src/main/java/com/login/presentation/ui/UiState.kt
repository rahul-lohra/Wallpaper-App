package com.login.presentation.ui

sealed class UiState
object UiStateInitial : UiState()
object UiStateLoading : UiState()
class UiStateSuccess(val isLoggedIn: Boolean) : UiState()
class UiStateFail(val th: Throwable) : UiState()

sealed class LoginState
object LoginStateLoading : LoginState()
object LoginStateSuccess : LoginState()
class LoginStateFail(th: Throwable) : LoginState()
