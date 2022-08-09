package com.rahul.notificationstest.di.modules

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

@Module
class AppInterceptorsModule @Inject constructor (val context: Context) {

    @Provides
    @IntoSet
    fun getChuckerInterceptor(): Interceptor = ChuckerInterceptor.Builder(context).build()

    @Provides
    @IntoSet
    fun getInterceptor(): Interceptor {
        return object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val response = chain.proceed(chain.request())
                if (response.isSuccessful) {

                } else {
                    //Send to server
                }
                return response
            }
        }
    }
}