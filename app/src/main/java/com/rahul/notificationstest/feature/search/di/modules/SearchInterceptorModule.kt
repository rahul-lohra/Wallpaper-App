package com.rahul.notificationstest.feature.search.di.modules

import com.rahul.notificationstest.feature.search.data.apis.unsplash.UnsplashInterceptor
import com.rahul.notificationstest.feature.search.di.qualifiers.Pexels
import com.rahul.notificationstest.feature.search.di.qualifiers.PixaBay
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.Response

@Module
class SearchInterceptorModule {

    @Provides
    @PixaBay
    @IntoSet
    fun getPixaBayInterceptor(): Interceptor {
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

    @Provides
    @Pexels
    @IntoSet
    fun getPexelsInterceptor(): Interceptor {
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

    @Provides
    @Pexels
    @IntoSet
    fun providesUnsplashInterceptor() = UnsplashInterceptor()
}