package com.search.domain

import com.dispatchers.DispatcherQualifiers
import com.search.data.repositories.FollowDataNotLoggedIn
import com.search.data.repositories.FollowDataPaginated
import com.search.data.repositories.SearchRepository
import com.search.domain.models.FollowDomainData
import com.search.domain.models.FollowDomainDataPaginated
import com.search.domain.models.FollowDomainEntity
import com.search.domain.models.FollowDomainDataNotLoggedIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class FollowersUseCase @Inject constructor(
    @Named(DispatcherQualifiers.IO) private val io: CoroutineDispatcher,
    @Named(DispatcherQualifiers.DEFAULT) private val default: CoroutineDispatcher,
    private val repository: SearchRepository
) {

    suspend fun getFollowersData(): Flow<FollowDomainData> = withContext(io) {
        repository.getFollowersData().flowOn(default).map { followData ->
            when (followData) {
                is FollowDataPaginated -> {
                    val list = followData.data.map {
                        FollowDomainEntity(it.name)
                    }
                    FollowDomainDataPaginated(list)
                }
                is FollowDataNotLoggedIn -> FollowDomainDataNotLoggedIn

            }
        }
    }
}