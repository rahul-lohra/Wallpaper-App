package com.rahul.notificationstest.di.component

import android.content.Context
import com.rahul.notificationstest.App
import com.rahul.notificationstest.di.modules.AppContextModule
import com.rahul.notificationstest.di.modules.AppNetworkModule
import com.rahul.notificationstest.di.scope.AppScope
import dagger.Component
import okhttp3.Interceptor
import retrofit2.Retrofit

@AppScope
@Component(
    modules = [
        AppContextModule::class,
        AppNetworkModule::class
    ],
)
interface AppComponent {
    fun inject(app: App)

    fun context(): Context
    fun retrofitBuilder(): Retrofit.Builder
    fun interceptorSet(): MutableSet<Interceptor>

    @Component.Factory
    interface Factory {
        fun create(appContextModule: AppContextModule): AppComponent
    }

}