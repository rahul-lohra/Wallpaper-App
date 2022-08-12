package com.rahul.notificationstest.feature.search.di.modules

import com.rahul.notificationstest.feature.search.data.apis.pexels.PexelsInterceptor
import com.rahul.notificationstest.feature.search.data.apis.pixabay.PixabayInterceptor
import com.rahul.notificationstest.feature.search.data.apis.unsplash.UnsplashInterceptor
import dagger.Binds
import dagger.Module

@Module
abstract class SearchInterceptorModule {

//    @Binds
//    abstract fun getPixaBayInterceptor(pixabayInterceptor: PixabayInterceptor): PixabayInterceptor
//
//    @Binds
//    abstract fun getPexelsInterceptor(pexelsInterceptor: PexelsInterceptor): PexelsInterceptor
//
//    @Binds
//    abstract fun providesUnsplashInterceptor(unsplashInterceptor: UnsplashInterceptor): UnsplashInterceptor
}