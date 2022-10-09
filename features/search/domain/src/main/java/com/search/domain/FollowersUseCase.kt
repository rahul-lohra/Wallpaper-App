package com.search.domain

import androidx.paging.PagingData
import com.search.data.repositories.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FollowersUseCase @Inject constructor(private val repository: SearchRepository){

    suspend fun getData(): Flow<String> {
        return repository.getDummyData()
    }

    fun getPagingData() = repository.getPagingPhotos()
}

sealed class FollowDomainData

class FollowPaginatedData(data: Flow<PagingData<String>>) : FollowDomainData()
object FollowPaginatedDataInitial : FollowDomainData()
object NotLoggedInData : FollowDomainData()
class Error(ex: Exception) : FollowDomainData()