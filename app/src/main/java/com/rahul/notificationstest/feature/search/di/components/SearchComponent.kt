package com.rahul.notificationstest.feature.search.di.components

import android.content.Context
import com.rahul.notificationstest.App
import com.rahul.notificationstest.di.component.AppComponent
import com.rahul.notificationstest.di.modules.AppContextModule
import com.rahul.notificationstest.di.modules.AppInterceptorsModule
import com.rahul.notificationstest.di.modules.AppNetworkModule
import com.rahul.notificationstest.di.scope.AppScope
import com.rahul.notificationstest.feature.search.data.apis.pixabay.PixabayApi
import com.rahul.notificationstest.feature.search.di.modules.SearchInterceptorModule
import com.rahul.notificationstest.feature.search.di.modules.SearchNetworkModule
import com.rahul.notificationstest.feature.search.di.qualifiers.PixaBay
import com.rahul.notificationstest.feature.search.di.scopes.SearchScope
import com.rahul.notificationstest.feature.search.ui.fragments.SearchFragment
import dagger.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject

@SearchScope
@Component(
    modules = [
        SearchInterceptorModule::class,
        SearchNetworkModule::class
    ],
    dependencies = [AppComponent::class]
)
interface SearchComponent {

    fun inject(fragment: SearchFragment)

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): SearchComponent
    }
}