package com.rahul.notificationstest.di.component

import android.content.Context
import com.rahul.notificationstest.App
import com.rahul.notificationstest.di.modules.AppContextModule
import com.rahul.notificationstest.di.modules.AppNetworkModule
import com.rahul.notificationstest.di.modules.ViewModelFactoryModule
import com.rahul.notificationstest.di.scope.AppScope
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@AppScope
@Component(
    modules = [
        AppContextModule::class,
        AppNetworkModule::class,
        ViewModelFactoryModule::class,
    ],
)
interface AppComponent {
    fun inject(app: App)

    fun context(): Context
    fun retrofitBuilder(): Retrofit.Builder
    fun okHttpClient(): OkHttpClient

    fun moshiConvertorFactory():MoshiConverterFactory

    @Component.Factory
    interface Factory {
        fun create(appContextModule: AppContextModule): AppComponent
    }

}