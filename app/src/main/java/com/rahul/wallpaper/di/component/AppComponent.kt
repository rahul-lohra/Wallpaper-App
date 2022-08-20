package com.rahul.wallpaper.di.component

import android.content.Context
import com.rahul.wallpaper.App
import com.rahul.wallpaper.di.modules.AppContextModule
import com.rahul.wallpaper.di.modules.AppNetworkModule
import com.rahul.wallpaper.di.modules.ViewModelFactoryModule
import com.rahul.wallpaper.di.scope.AppScope
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