package com.rahul.wallpaper.feature.login.domain.usecase

import com.rahul.wallpaper.feature.credentials.CredentialsStorage
import com.rahul.wallpaper.feature.search.data.apis.unsplash.AuthTokenResponse
import javax.inject.Inject

class UnsplashFakeLoginUseCase @Inject constructor(val credentialsStorage: CredentialsStorage) :
    UnsplashLoginContract {

    override suspend fun performLogin(code:String) {
//        credentialsStorage.saveCredentials()
    }
}