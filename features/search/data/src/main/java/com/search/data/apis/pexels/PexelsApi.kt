package com.search.data.apis.pexels

import androidx.annotation.IntRange
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsApi {
    object Config {
        //TODO Rahul API Keys
        const val API_KEY = "BuildConfig.PEXELS_API_KEY"
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