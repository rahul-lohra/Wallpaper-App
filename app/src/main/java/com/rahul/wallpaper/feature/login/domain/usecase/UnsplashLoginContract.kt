package com.rahul.wallpaper.feature.login.domain.usecase

import com.rahul.wallpaper.feature.login.presentation.ui.LoginState

interface UnsplashLoginContract {
    suspend fun performLogin(url: String?): LoginState
}