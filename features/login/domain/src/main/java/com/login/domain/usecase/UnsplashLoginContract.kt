package com.login.domain.usecase

import android.net.Uri
import com.login.domain.models.LoginDomainState


interface UnsplashLoginContract {
    suspend fun performLogin(url: String?): LoginDomainState
    suspend fun processLoginCompleted(uri: Uri)
}