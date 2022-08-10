package com.rahul.notificationstest.feature.search.data.apis.unsplash

import androidx.annotation.StringDef
import com.rahul.notificationstest.feature.search.data.apis.pixabay.PixabayApi.QueryParams.IMAGE_TYPE
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    object Config {
        internal const val ADDRESS_KEY = "TfdRLrVqwVkAs8I1hKPrWLlGA3nhMJUGgFQ1Mtrwlc8"
        private const val SECRET_KEY = "D3AbifGGwi0cIDrUHW-SzhkAiA8k2lDy6rGq1VBmfLw"
        const val BASE_URL = "https://api.unsplash.com"
    }

    object QueryParams {
        const val PAGE = "page"
    }

    object RequestHeaders {
        const val AUTHORIZATION = "Authorization"
        private const val CLIENT_ID = "Client-ID"
        const val AUTHORIZATION_VALUE = "$CLIENT_ID ${Config.ADDRESS_KEY}"

        object AcceptVersion {
            const val KEY = "Accept-Version"
            const val VALUE = "v1"
        }
    }

    object ResponseHeaders {
        const val RATE_LIMIT = "X-Ratelimit-Limit"
        const val RATE_LIMIT_REMAINING = "X-Ratelimit-Remaining"
    }

    @StringDef(ImageType.ALL, ImageType.PHOTO, ImageType.ILLUSTRATION, ImageType.VECTOR)
    @Retention
    annotation class ImageType {
        companion object {
            const val ALL = "all"
            const val PHOTO = "photo"
            const val ILLUSTRATION = "illustration"
            const val VECTOR = "vector"
        }
    }

    @GET("/photos")
    fun getPhotos(
        @Query(QueryParams.PAGE) pageNumber: Int
    ): UnsplashResponse
}