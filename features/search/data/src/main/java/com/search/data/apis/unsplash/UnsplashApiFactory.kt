package com.search.data.apis.unsplash

import retrofit2.Retrofit
import javax.inject.Inject

class UnsplashApiFactory @Inject constructor(private val retrofitBuilder: dagger.Lazy<Retrofit.Builder>) {

    fun unsplashApi(): UnsplashApi {
        val retrofit = retrofitBuilder.get()
            .baseUrl(UnsplashApi.Config.BASE_URL)
            .build()

        return retrofit.create(UnsplashApi::class.java)
    }
}