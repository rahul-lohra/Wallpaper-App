package com.rahul.notificationstest.feature.search.data.datasource

import com.rahul.notificationstest.feature.search.data.apis.pexels.PexelsApi
import com.rahul.notificationstest.feature.search.data.apis.pixabay.PixabayApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val pexelsApi:PexelsApi, pixabayApi: PixabayApi) {
}