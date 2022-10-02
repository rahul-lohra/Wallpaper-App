package com.login.domain.usecase

import com.login.domain.models.LoginDomainState


interface UnsplashLoginContract {
    suspend fun performLogin(url: String?): LoginDomainState
}