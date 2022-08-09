package com.rahul.notificationstest.di.component

import android.content.Context
import com.rahul.notificationstest.App
import com.rahul.notificationstest.di.modules.AppContextModule
import com.rahul.notificationstest.di.modules.AppNetworkModule
import com.rahul.notificationstest.di.scope.AppScope
import com.rahul.notificationstest.feature.search.di.components.SearchComponent
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import okhttp3.OkHttp
import retrofit2.Retrofit

@AppScope
@Component(modules = [AppContextModule::class, AppNetworkModule::class])
interface AppComponent {
    fun inject(app: App)

    @Component.Factory
    interface Factory {
        fun create(context: Context): AppComponent
    }

}