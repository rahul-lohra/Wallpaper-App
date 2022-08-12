package com.rahul.notificationstest.feature.search.di.modules

import com.rahul.notificationstest.feature.search.data.apis.pexels.PexelsApi
import com.rahul.notificationstest.feature.search.data.apis.pixabay.PixabayApi
import com.rahul.notificationstest.feature.search.data.apis.unsplash.UnsplashApi
import com.rahul.notificationstest.feature.search.di.scopes.SearchScope
import dagger.Lazy
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SearchNetworkModule {

    @SearchScope
    @Provides
    fun providePixaBayApi(
        retrofitBuilder: Lazy<Retrofit.Builder>): PixabayApi {
        val retrofit = retrofitBuilder.get()
            .baseUrl(PixabayApi.Config.BASE_URL)
            .build()

        return retrofit.create(PixabayApi::class.java)
    }

    @SearchScope
    @Provides
    fun providePexelsApi(
        retrofitBuilder: Lazy<Retrofit.Builder>
    ): PexelsApi {
        val retrofit = retrofitBuilder.get()
            .baseUrl(PixabayApi.Config.BASE_URL)
            .build()

        return retrofit.create(PexelsApi::class.java)
    }

    @SearchScope
    @Provides
    fun providesUnsplashApi(
        retrofitBuilder: Lazy<Retrofit.Builder>
    ): UnsplashApi {
        val retrofit = retrofitBuilder.get()
            .baseUrl(UnsplashApi.Config.BASE_URL)
            .build()

        return retrofit.create(UnsplashApi::class.java)
    }
}