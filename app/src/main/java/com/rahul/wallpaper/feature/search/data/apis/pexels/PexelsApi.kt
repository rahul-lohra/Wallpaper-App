package com.rahul.wallpaper.feature.search.data.apis.pexels

import androidx.annotation.IntRange
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsApi {
    object Config {
        const val API_KEY = "563492ad6f91700001000001412aae37559a47e78a9caf52139a4669"
    }

    object QueryParams {
        const val PAGE = "page"
        const val PER_PAGE = "per_page"
    }

    @GET("curated")
    fun getPhotos(
        @Query(QueryParams.PAGE) @IntRange(from = 1) pageNumber: Int,
        @Query(QueryParams.PER_PAGE) pageCount: Int
    ): PexelsResponse
}