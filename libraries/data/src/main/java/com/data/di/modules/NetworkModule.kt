package com.data.di.modules

//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.variant.BuildVariant
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

@Module
//@InstallIn(SingletonComponent::class)
class NetworkModule @Inject constructor() {

//    @Provides
//    fun setupOkHttp(context: Context, appInterceptorsModule: InterceptorsModule): OkHttpClient {
//        val builder = OkHttpClient.Builder()
//        builder.interceptors().addAll(appInterceptorsModule.getInterceptorList(context))
//        return builder.build()
//    }

    //    @AppScope
//    @Provides
//    fun setupRetroFit(client: dagger.Lazy<OkHttpClient>, moshiConverterFactory:MoshiConverterFactory): Retrofit {
//        return Retrofit.Builder()
//            .client(client.get())
//            .baseUrl("")
//            .addConverterFactory(moshiConverterFactory)
//            .build()
//    }
    @Provides
    fun setupOkHttp(context: Context, chuckerInterceptor: ChuckerInterceptor): OkHttpClient {
        val cacheSize = 10 * 1024 * 1024L // 10MB
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(chuckerInterceptor)
        if (BuildVariant.isDebug()) {
            builder.addInterceptor(getLogginInterceptor())
        }
        builder.cache(Cache(context.cacheDir, cacheSize))
        return builder.build()
    }

    //    @AppScope
    @Provides
    fun provideMoshiConvertorFactory() = MoshiConverterFactory.create()

    @Provides
    fun provideMoshi() = Moshi.Builder().build()


    @Provides
    fun getLogginInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }
}