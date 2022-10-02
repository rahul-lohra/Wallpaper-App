package com.search.data.di.modules

import com.search.data.apis.unsplash.UnsplashApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SearchNetworkModule {

//    @Provides
//    fun providePixaBayApi(
//        okHttpClient: OkHttpClient,
//        retrofitBuilder: Lazy<Retrofit.Builder>,
//        interceptor: PixabayInterceptor
//    ): PixabayApi {
//
//        val retrofit = retrofitBuilder.get()
//            .baseUrl(PixabayApi.Config.BASE_URL)
//            .client(provideOkHttpClient(okHttpClient, interceptor))
//            .build()
//
//        return retrofit.create(PixabayApi::class.java)
//    }

//    @Provides
//    fun providePexelsApi(
//        okHttpClient: OkHttpClient,
//        retrofitBuilder: Lazy<Retrofit.Builder>,
//        interceptor: PexelsInterceptor
//    ): PexelsApi {
//
//        val retrofit = retrofitBuilder.get()
//            .baseUrl(PixabayApi.Config.BASE_URL)
//            .client(provideOkHttpClient(okHttpClient, interceptor))
//            .build()
//
//        return retrofit.create(PexelsApi::class.java)
//    }

    //TODO Rahul this is not required
//    @Provides
//    fun provideOkHttpClient():OkHttpClient{
//        return OkHttpClient.Builder().build()
//    }

    //TODO This method should be removed from here and should be part of unsplash module. Its source code is altered check original from master branch
    @Provides
    fun providesUnsplashApi(): UnsplashApi {
        return Retrofit.Builder().build().create(UnsplashApi::class.java)
    }

//    @Provides
//    fun provideOkHttpClient(
//        okHttpClient: OkHttpClient,
//        interceptor: Interceptor
//    ): OkHttpClient {
//        return okHttpClient.newBuilder()
//            .addInterceptor(interceptor)
//            .build()
//    }
}