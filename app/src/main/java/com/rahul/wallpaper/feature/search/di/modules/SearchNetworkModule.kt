package com.rahul.wallpaper.feature.search.di.modules

import com.rahul.wallpaper.feature.search.data.apis.pexels.PexelsApi
import com.rahul.wallpaper.feature.search.data.apis.pexels.PexelsInterceptor
import com.rahul.wallpaper.feature.search.data.apis.pixabay.PixabayApi
import com.rahul.wallpaper.feature.search.data.apis.pixabay.PixabayInterceptor
import com.rahul.wallpaper.feature.search.data.apis.unsplash.UnsplashApi
import com.rahul.wallpaper.feature.search.data.apis.unsplash.UnsplashInterceptor
import com.rahul.wallpaper.feature.search.di.scopes.SearchScope
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
class SearchNetworkModule {

    @Provides
    fun providePixaBayApi(
        okHttpClient: OkHttpClient,
        retrofitBuilder: Lazy<Retrofit.Builder>,
        interceptor: PixabayInterceptor
    ): PixabayApi {

        val retrofit = retrofitBuilder.get()
            .baseUrl(PixabayApi.Config.BASE_URL)
            .client(provideOkHttpClient(okHttpClient, interceptor))
            .build()

        return retrofit.create(PixabayApi::class.java)
    }

    @Provides
    fun providePexelsApi(
        okHttpClient: OkHttpClient,
        retrofitBuilder: Lazy<Retrofit.Builder>,
        interceptor: PexelsInterceptor
    ): PexelsApi {

        val retrofit = retrofitBuilder.get()
            .baseUrl(PixabayApi.Config.BASE_URL)
            .client(provideOkHttpClient(okHttpClient, interceptor))
            .build()

        return retrofit.create(PexelsApi::class.java)
    }

    //TODO This method should be removed from here and should be part of unsplash module
    @Provides
    fun providesUnsplashApi(
        okHttpClient: OkHttpClient,
        retrofitBuilder: Lazy<Retrofit.Builder>,
        interceptor: UnsplashInterceptor
    ): UnsplashApi {

        val retrofit = retrofitBuilder.get()
            .baseUrl(UnsplashApi.Config.BASE_URL)
            .client(provideOkHttpClient(okHttpClient, interceptor))
            .build()

        return retrofit.create(UnsplashApi::class.java)
    }

    private fun provideOkHttpClient(
        okHttpClient: OkHttpClient,
        interceptor: Interceptor
    ): OkHttpClient {
        return okHttpClient.newBuilder()
            .addInterceptor(interceptor)
            .build()
    }
}