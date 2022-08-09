package com.rahul.notificationstest.feature.search.di.modules

import com.rahul.notificationstest.di.modules.AppNetworkModule
import com.rahul.notificationstest.feature.search.data.apis.pexels.Pexels
import com.rahul.notificationstest.feature.search.data.apis.pixabay.Pixabay
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [AppNetworkModule::class])
class SearchNetworkModule {

    @Provides
    fun providePixaBayApi(retrofitBuilder: Retrofit.Builder): Pixabay {
        val retrofit = retrofitBuilder
            .baseUrl(Pixabay.Config.BASE_URL)
            .build()

        return retrofit.create(Pixabay::class.java)
    }

    @Provides
    fun providePexelsApi(retrofitBuilder: Retrofit.Builder): Pexels {
        val retrofit = retrofitBuilder
            .baseUrl(Pixabay.Config.BASE_URL)
            .build()

        return retrofit.create(Pexels::class.java)
    }
}