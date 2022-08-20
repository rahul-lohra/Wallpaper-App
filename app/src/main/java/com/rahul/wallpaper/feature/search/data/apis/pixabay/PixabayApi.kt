package com.rahul.wallpaper.feature.search.data.apis.pixabay

import androidx.annotation.StringDef
import com.rahul.wallpaper.feature.search.data.apis.pixabay.PixabayApi.QueryParams.IMAGE_TYPE
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
    object Config {
        private const val API_KEY = "29120282-1da40772ceadefa581cf310a0"
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