package com.rahul.wallpaper.feature.search.data.repositories

import androidx.paging.PagingData
import com.rahul.wallpaper.feature.search.data.datasource.DummyDataProvider
import com.rahul.wallpaper.feature.search.data.datasource.LocalDataSource
import com.rahul.wallpaper.feature.search.data.datasource.RemoteDataSource
import com.rahul.wallpaper.feature.search.domain.usecase.FollowDomainData
import com.rahul.wallpaper.feature.search.domain.usecase.FollowPaginatedData
import com.rahul.wallpaper.feature.search.domain.usecase.NotLoggedInData
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