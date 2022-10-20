package com.login.domain.usecase

import android.net.Uri
import com.login.domain.models.LoginDomainState


interface UnsplashLoginContract {
    fun getLoginUri(): String
    suspend fun performLogin(url: String): LoginDomainState
}