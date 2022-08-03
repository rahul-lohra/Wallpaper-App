package com.rahul.notificationstest.network

import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient {

    fun setupOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(getInterceptor())
            .build()
    }

    fun setupRetroFit(client: OkHttpClient) {
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

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