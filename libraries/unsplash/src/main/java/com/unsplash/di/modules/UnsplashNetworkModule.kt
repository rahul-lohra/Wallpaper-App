package com.unsplash.di.modules

import com.data.keyvaluedatasource.CredentialStorage
import com.unsplash.UnsplashApi
import com.unsplash.UnsplashInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class UnsplashNetworkModule {

    @Provides
    fun providesUnsplashApi(
        lazyOkHttpClient: dagger.Lazy<OkHttpClient>,
        moshiConverterFactory: MoshiConverterFactory,
        credentialStorage: CredentialStorage
    ): UnsplashApi {
        val okHttpClient =
            addInterceptorToOkHttp(lazyOkHttpClient.get(), UnsplashInterceptor(credentialStorage))
        val retrofit = Retrofit.Builder()
            .baseUrl(UnsplashApi.Config.BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .client(okHttpClient)
            .build()

        return retrofit.create(UnsplashApi::class.java)
    }

    private fun addInterceptorToOkHttp(
        okHttpClient: OkHttpClient,
        interceptor: Interceptor
    ): OkHttpClient {
        return okHttpClient.newBuilder()
            .addInterceptor(interceptor)
            .build()
    }
}