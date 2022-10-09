package com.search.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.unsplash.UnsplashApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    val unsplashApi: UnsplashApi
) {

    fun getPagingPhotos(): Flow<PagingData<String>> {
        return Pager(
            config = PagingConfig(10),
            pagingSourceFactory = {
                PhotosPagingSource(unsplashApi)
            }).flow
    }
}