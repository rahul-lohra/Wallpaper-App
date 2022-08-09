package com.rahul.notificationstest.feature.search.di.modules

import com.rahul.notificationstest.di.modules.AppNetworkModule
import com.rahul.notificationstest.feature.search.data.apis.pexels.PexelsApi
import com.rahul.notificationstest.feature.search.data.apis.pixabay.PixabayApi
import com.rahul.notificationstest.feature.search.di.scopes.SearchScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SearchNetworkModule {

    @SearchScope
    @Provides
    fun providePixaBayApi(retrofitBuilder: Retrofit.Builder): PixabayApi {
        val retrofit = retrofitBuilder
            .baseUrl(PixabayApi.Config.BASE_URL)
            .build()

        return retrofit.create(PixabayApi::class.java)
    }

    @SearchScope
    @Provides
    fun providePexelsApi(retrofitBuilder: Retrofit.Builder): PexelsApi {
        val retrofit = retrofitBuilder
            .baseUrl(PixabayApi.Config.BASE_URL)
            .build()

        return retrofit.create(PexelsApi::class.java)
    }
}