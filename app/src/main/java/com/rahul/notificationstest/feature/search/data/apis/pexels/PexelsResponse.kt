package com.rahul.notificationstest.feature.search.data.apis.pexels

import com.squareup.moshi.Json

data class PexelsResponse(

    @Json(name = "page") var page: Int? = null,
    @Json(name = "per_page") var perPage: Int? = null,
    @Json(name = "photos") var photos: ArrayList<Photos> = arrayListOf(),
    @Json(name = "total_results") var totalResults: Int? = null,
    @Json(name = "next_page") var nextPage: String? = null
) {
    data class Src(

        @Json(name = "original") var original: String? = null,
        @Json(name = "large2x") var large2x: String? = null,
        @Json(name = "large") var large: String? = null,
        @Json(name = "medium") var medium: String? = null,
        @Json(name = "small") var small: String? = null,
        @Json(name = "portrait") var portrait: String? = null,
        @Json(name = "landscape") var landscape: String? = null,
        @Json(name = "tiny") var tiny: String? = null

    )

    data class Photos(

        @Json(name = "id") var id: Int? = null,
        @Json(name = "width") var width: Int? = null,
        @Json(name = "height") var height: Int? = null,
        @Json(name = "url") var url: String? = null,
        @Json(name = "photographer") var photographer: String? = null,
        @Json(name = "photographer_url") var photographerUrl: String? = null,
        @Json(name = "photographer_id") var photographerId: Int? = null,
        @Json(name = "avg_color") var avgColor: String? = null,
        @Json(name = "src") var src: Src? = Src(),
        @Json(name = "liked") var liked: Boolean? = null,
        @Json(name = "alt") var alt: String? = null

    )
}