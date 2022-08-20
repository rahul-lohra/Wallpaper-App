package com.rahul.wallpaper.feature.search.data.apis.unsplash

import dagger.Lazy
import retrofit2.Retrofit
import javax.inject.Inject

class UnsplashApiFactory @Inject constructor(val retrofitBuilder: Lazy<Retrofit.Builder>) {

    fun unsplashApi(): UnsplashApi {
        val retrofit = retrofitBuilder.get()
            .baseUrl(UnsplashApi.Config.BASE_URL)
            .build()

        return retrofit.create(UnsplashApi::class.java)
    }
}