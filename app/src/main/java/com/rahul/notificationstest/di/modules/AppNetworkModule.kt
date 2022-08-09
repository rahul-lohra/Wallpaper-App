package com.rahul.notificationstest.di.modules

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module(includes = [AppInterceptorsModule::class])
class AppNetworkModule {

    @Provides
    fun setupOkHttp1(interceptors: MutableSet<Interceptor>): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.interceptors().addAll(interceptors)
        return builder.build()
    }

    //    @AppScope
    @Provides
    fun setupRetroFit(client: dagger.Lazy<OkHttpClient>): Retrofit.Builder {
        return Retrofit.Builder()
            .client(client.get())
            .addConverterFactory(MoshiConverterFactory.create())
    }
}