package com.rahul.wallpaper.di.component

import android.content.Context
import com.rahul.wallpaper.App
import com.rahul.wallpaper.data.keyvaluedatasource.KeyValueStorage
import com.rahul.wallpaper.di.modules.*
import com.rahul.wallpaper.di.scope.AppScope
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@AppScope
@Component(
    modules = [
        AppContextModule::class,
        AppNetworkModule::class,
        ViewModelFactoryModule::class,
        StorageModule::class,
        DispatcherModule::class
    ],
)
interface AppComponent {
    fun inject(app: App)

    fun context(): Context
    fun retrofitBuilder(): Retrofit.Builder
    fun okHttpClient(): OkHttpClient

    fun moshiConvertorFactory(): MoshiConverterFactory
    fun keyValueStorage(): KeyValueStorage

    @Component.Factory
    interface Factory {
        fun create(appContextModule: AppContextModule): AppComponent
    }

}