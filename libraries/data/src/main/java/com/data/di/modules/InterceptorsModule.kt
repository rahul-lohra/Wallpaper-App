package com.data.di.modules

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

@Module
class InterceptorsModule @Inject constructor() {

    @Provides
    fun getChuckerInterceptor(context: Context): ChuckerInterceptor =
        ChuckerInterceptor.Builder(context).build()


    private fun getLogginInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }


    private fun getInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            if (response.isSuccessful) {

            } else {
                //Send to server
            }
            response
        }
    }

    fun getInterceptorList(context: Context): List<Interceptor> = listOf(
        getInterceptor(),
        getLogginInterceptor(),
        getChuckerInterceptor(context)
    )
}