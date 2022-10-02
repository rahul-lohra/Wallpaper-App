package com.search.data.apis.pixabay

import androidx.annotation.StringDef
import com.search.data.apis.pixabay.PixabayApi.QueryParams.IMAGE_TYPE
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
    object Config {
        //TODO Rahul API Keys
        private const val API_KEY = "BuildConfig.PIXABAY_API_KEY"
        const val BASE_URL = "https://pixabay.com/api/?key=$API_KEY"
    }

    object QueryParams {
        const val Q = "q"
        const val IMAGE_TYPE = "image_type"
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

    @GET("")
    fun getPhotos(
        @Query(QueryParams.Q) query: String,
        @Query(IMAGE_TYPE) @ImageType imageType: String = ImageType.PHOTO
    ): PixabayResponse
}