package com.rahul.wallpaper.feature.search.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rahul.wallpaper.feature.search.data.apis.pexels.PexelsApi
import com.rahul.wallpaper.feature.search.data.apis.pixabay.PixabayApi
import com.rahul.wallpaper.feature.search.data.apis.unsplash.UnsplashApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    val pexelsApi: PexelsApi,
    val pixabayApi: PixabayApi,
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