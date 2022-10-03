package com.di.app.component

import android.content.Context
import com.data.di.modules.NetworkModule
import com.di.app.AppContract
import com.di.app.modules.AppContextModule
import com.di.app.scope.AppScope
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@AppScope
@Component(
    modules = [
        AppContextModule::class,
        NetworkModule::class
    ],
)
interface AppComponent {
//    fun inject(app: AppContract)

    fun context(): Context
//    fun retrofitClient(): Retrofit
    fun okHttpClient(): OkHttpClient

//    fun moshiConvertorFactory(): MoshiConverterFactory
//    fun keyValueStorage(): KeyValueStorage

    @Component.Factory
    interface Factory {
        fun create(appContextModule: AppContextModule): AppComponent
    }

}