package com.search.data.repositories

import androidx.paging.PagingData
import com.search.data.datasource.DummyDataProvider
import com.search.data.datasource.LocalDataSource
import com.search.data.datasource.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val dummyDataProvider: DummyDataProvider,
) {

    suspend fun getDummyData(): Flow<String> {
        return dummyDataProvider.getData()
    }

    fun getPagingPhotos(): Flow<PagingData<String>> = remoteDataSource.getPagingPhotos()

    //TODO Rahul Create data's layer Follow data
    fun getFollowersData() =
        flow<FollowDomainData> {
            localDataSource.credentialsStorage.getUserId()
                .collect { userId ->
                    if (userId.isNotEmpty()) {
                        emit(FollowPaginatedData(getPagingPhotos()))
                    } else {
                        emit(NotLoggedInData)
                    }
                }
        }
}

sealed class FollowDomainData

class FollowPaginatedData(data: Flow<PagingData<String>>) : FollowDomainData()
object FollowPaginatedDataInitial : FollowDomainData()
object NotLoggedInData : FollowDomainData()
class Error(ex: Exception) : FollowDomainData()