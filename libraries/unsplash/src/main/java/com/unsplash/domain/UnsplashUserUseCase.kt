package com.unsplash.domain

import com.unsplash.data.UnsplashRepository
import javax.inject.Inject

class UnsplashUserUseCase @Inject constructor(private val repository: UnsplashRepository) {

    suspend fun getUserData(userName: String?) = repository.getUserData(userName)
}