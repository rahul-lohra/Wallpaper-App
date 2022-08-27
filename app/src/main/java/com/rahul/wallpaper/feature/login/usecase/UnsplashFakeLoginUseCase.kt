package com.rahul.wallpaper.feature.login.usecase

import com.rahul.wallpaper.feature.credentials.CredentialsStorage
import javax.inject.Inject

class UnsplashFakeLoginUseCase @Inject constructor(val credentialsStorage: CredentialsStorage) : UnsplashLoginContract {

    override suspend fun performLogin() {
        credentialsStorage.saveCredentials()
    }
}