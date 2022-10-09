package com.login.presentation.ui

sealed class UiState
class UiStateInitial : UiState()
class UiStateLoading : UiState()
class UiStateSuccess : UiState()
class UiStateFail(ex: Exception) : UiState()

sealed class LoginState
object LoginStateLoading : LoginState()
object LoginStateSuccess : LoginState()
class LoginStateFail(ex: Exception) : LoginState()
