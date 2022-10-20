package com.search.data.repositories

import androidx.paging.PagingData
import com.search.data.datasource.LocalDataSource
import com.search.data.datasource.RemoteDataSource
import com.unsplash.FollowingEntity
import com.unsplash.data.UnsplashCredentialStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) {

    suspend fun isLoggedIn() = localDataSource.isLoggedIn()

    fun getPagingPhotos(): Flow<PagingData<String>> = remoteDataSource.getPagingPhotos()

    suspend fun getFollowersData(): Flow<FollowData> =
        flow<FollowData> {
            localDataSource.credentialsStorage.getUserId()
                .collect { userId ->
                    if (userId.isNotEmpty() && !UnsplashCredentialStorage.userData?.username.isNullOrEmpty()) {
                        val userName = UnsplashCredentialStorage.userData?.username!!
                        emit(FollowDataPaginated(remoteDataSource.getFollowing(userName)))
                    } else {
                        emit(FollowDataNotLoggedIn)
                    }
                }
        }
}

sealed class FollowData
class FollowDataPaginated(val data: List<FollowingEntity>) : FollowData()
object FollowDataNotLoggedIn : FollowData()