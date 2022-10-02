package com.search.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.search.data.apis.unsplash.UnsplashApi
import com.search.data.datasource.PhotosPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
//    val pexelsApi: PexelsApi,
//    val pixabayApi: PixabayApi,
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