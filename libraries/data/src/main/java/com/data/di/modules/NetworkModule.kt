package com.data.di.modules

import android.content.Context
import com.data.BuildConfig
import com.variant.BuildVariant
import dagger.Module
import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
//@InstallIn(SingletonComponent::class)
class NetworkModule {

//    @Provides
//    fun setupOkHttp(context: Context, appInterceptorsModule: InterceptorsModule): OkHttpClient {
//        val builder = OkHttpClient.Builder()
//        builder.interceptors().addAll(appInterceptorsModule.getInterceptorList(context))
//        return builder.build()
//    }

    //    @AppScope
    @Provides
    fun setupRetroFit(
        client: dagger.Lazy<OkHttpClient>,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(client.get())
            .addConverterFactory(moshiConverterFactory)
            .build()
    }
    @Provides
    fun setupOkHttp( context: Context): OkHttpClient {
        val cacheSize = 10 * 1024 * 1024L // 10MB
        val builder = OkHttpClient.Builder()
        if(BuildVariant.isDebug()) {
            builder.addInterceptor(getLogginInterceptor())
        }
        builder.cache(Cache(context.cacheDir, cacheSize))
        return builder.build()
    }
//    @AppScope
    @Provides
    fun provideMoshiConvertorFactory() = MoshiConverterFactory.create()

    @Provides
    fun getLogginInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }
}