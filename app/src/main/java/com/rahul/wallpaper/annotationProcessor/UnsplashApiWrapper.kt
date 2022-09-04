package com.rahul.wallpaper.annotationProcessor

import com.rahul.apiresult_processor.annotations.ApiResultWrapper
import com.rahul.wallpaper.feature.search.data.apis.unsplash.UnsplashApi

@ApiResultWrapper(UnsplashApi::class)
abstract class UnsplashApiWrapper{}