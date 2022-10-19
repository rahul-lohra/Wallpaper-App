package com.search.domain

import com.search.data.repositories.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: SearchRepository) {

    fun getPagingData() = repository.getPagingPhotos()
}