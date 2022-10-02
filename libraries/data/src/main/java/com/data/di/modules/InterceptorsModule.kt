package com.data.di.modules

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class InterceptorsModule @Inject constructor() {

    private fun getChuckerInterceptor(context: Context): Interceptor =
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