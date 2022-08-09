package com.rahul.notificationstest.feature.search.data.datasource

import com.rahul.notificationstest.feature.search.data.apis.pexels.Pexels
import com.rahul.notificationstest.feature.search.data.apis.pixabay.Pixabay
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val pexelsApi:Pexels, pixabayApi: Pixabay) {
}