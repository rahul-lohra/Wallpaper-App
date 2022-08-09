package com.rahul.notificationstest.di.modules

import android.content.Context
import com.rahul.notificationstest.di.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module(includes = [AppInterceptorsModule::class])
class AppNetworkModule {

//    @AppScope
    @Provides
    fun setupOkHttp(interceptors: MutableSet<Interceptor>): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.interceptors().addAll(interceptors)
        return builder.build()
    }

//    @AppScope
    @Provides
    fun setupRetroFit(client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
    }
}