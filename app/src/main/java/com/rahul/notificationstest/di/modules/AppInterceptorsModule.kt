package com.rahul.notificationstest.di.modules

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

@Module
class AppInterceptorsModule {

    @Provides
    @IntoSet
    fun getChuckerInterceptor(context: Context): Interceptor =
        ChuckerInterceptor.Builder(context).build()


    @Provides
    @IntoSet
    fun getLogginInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }


    @Provides
    @IntoSet
    fun getInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            if (response.isSuccessful) {

            } else {
                //Send to server
            }
            response
        }
    }
}