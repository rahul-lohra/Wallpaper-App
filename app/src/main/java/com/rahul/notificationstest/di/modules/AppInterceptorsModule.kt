package com.rahul.notificationstest.di.modules

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.rahul.notificationstest.di.scope.AppScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

@Module
class AppInterceptorsModule {

//    @Provides
//    @IntoSet
//    fun getChuckerInterceptor(): Interceptor =
//        ChuckerInterceptor.Builder(context).build()

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