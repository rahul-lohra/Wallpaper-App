package com.rahul.notificationstest.feature.search.domain.usecase

import com.rahul.notificationstest.feature.search.data.repositories.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: SearchRepository) {

    suspend fun getData():Flow<String> {
        return repository.getDummyData()
    }
}