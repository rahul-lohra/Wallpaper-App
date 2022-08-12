package com.rahul.notificationstest.di.modules

import android.content.Context
import com.rahul.notificationstest.di.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class AppNetworkModule {

    @Provides
    fun setupOkHttp(context: Context, appInterceptorsModule: AppInterceptorsModule): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.interceptors().addAll(appInterceptorsModule.getInterceptorList(context))
        return builder.build()
    }

    //    @AppScope
    @Provides
    fun setupRetroFit(
        client: dagger.Lazy<OkHttpClient>,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .client(client.get())
            .addConverterFactory(moshiConverterFactory)
    }

    @AppScope
    @Provides
    fun provideMoshiConvertorFactory() = MoshiConverterFactory.create()
}