package com.rahul.wallpaper.di.component

import android.content.Context
import com.data.di.modules.NetworkModule
import com.data.keyvaluedatasource.KeyValueStorage
import com.rahul.wallpaper.App
import com.rahul.wallpaper.di.modules.AppContextModule
import com.rahul.wallpaper.di.scope.AppScope
import dagger.Component
import okhttp3.OkHttpClient

@AppScope
@Component(
    modules = [
        AppContextModule::class,
        NetworkModule::class
    ],
)
interface AppComponent {
    fun inject(app: App)

    fun context(): Context
//    fun retrofitBuilder(): Retrofit.Builder
//    fun okHttpClient(): OkHttpClient

//    fun moshiConvertorFactory(): MoshiConverterFactory
//    fun keyValueStorage(): KeyValueStorage

    @Component.Factory
    interface Factory {
        fun create(appContextModule: AppContextModule): AppComponent
    }

}