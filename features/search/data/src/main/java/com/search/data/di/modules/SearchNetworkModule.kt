package com.search.data.di.modules

import com.search.data.apis.unsplash.UnsplashApi
import com.search.data.apis.unsplash.UnsplashInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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
//    fun provideOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder().build()
//    }



    //TODO This method should be removed from here and should be part of unsplash module. Its source code is altered check original from master branch
    @Provides
    fun providesUnsplashApi(lazyOkHttpClient: dagger.Lazy<OkHttpClient>, moshiConverterFactory: MoshiConverterFactory): UnsplashApi {
        val okHttpClient =
            addInterceptorToOkHttp(lazyOkHttpClient.get(), UnsplashInterceptor())
        val retrofit = Retrofit.Builder()
            .baseUrl(UnsplashApi.Config.BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .client(okHttpClient)
            .build()

        return retrofit.create(UnsplashApi::class.java)
    }

//    @Provides
//    fun providesUnsplashApi2(lazyRetrofit: dagger.Lazy<Retrofit>,lazyOkHttpClient: dagger.Lazy<OkHttpClient>): UnsplashApi {
//        val okHttpClient =
//            addInterceptorToOkHttp(lazyOkHttpClient.get(), UnsplashInterceptor())
//        val retrofit = lazyRetrofit.get().newBuilder()
//            .baseUrl(UnsplashApi.Config.BASE_URL)
//            .client(okHttpClient)
//            .build()
//
//        return retrofit.create(UnsplashApi::class.java)
//    }

    //    @Provides
    fun addInterceptorToOkHttp(
        okHttpClient: OkHttpClient,
        interceptor: Interceptor
    ): OkHttpClient {
        return okHttpClient.newBuilder()
            .addInterceptor(interceptor)
            .build()
    }
}