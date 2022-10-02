package com.data.di.modules

import dagger.Module

@Module
class NetworkModule {

//    @Provides
//    fun setupOkHttp(context: Context, appInterceptorsModule: InterceptorsModule): OkHttpClient {
//        val builder = OkHttpClient.Builder()
//        builder.interceptors().addAll(appInterceptorsModule.getInterceptorList(context))
//        return builder.build()
//    }

    //    @AppScope
//    @Provides
//    fun setupRetroFit(
//        client: dagger.Lazy<OkHttpClient>,
//        moshiConverterFactory: MoshiConverterFactory
//    ): Retrofit.Builder {
//        return Retrofit.Builder()
//            .client(client.get())
//            .addConverterFactory(moshiConverterFactory)
//    }

//    @AppScope
//    @Provides
//    fun provideMoshiConvertorFactory() = MoshiConverterFactory.create()
}