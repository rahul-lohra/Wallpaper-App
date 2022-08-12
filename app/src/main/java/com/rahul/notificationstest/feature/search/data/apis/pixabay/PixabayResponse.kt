package com.rahul.notificationstest.feature.search.data.apis.pixabay

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PixabayResponse(

    @Json(name = "total") var total: Int? = null,
    @Json(name = "totalHits") var totalHits: Int? = null,
    @Json(name = "hits") var hits: ArrayList<Hits> = arrayListOf()

) {
    @JsonClass(generateAdapter = true)
    data class Hits(

        @Json(name = "id") var id: Int? = null,
        @Json(name = "pageURL") var pageURL: String? = null,
        @Json(name = "type") var type: String? = null,
        @Json(name = "tags") var tags: String? = null,
        @Json(name = "previewURL") var previewURL: String? = null,
        @Json(name = "previewWidth") var previewWidth: Int? = null,
        @Json(name = "previewHeight") var previewHeight: Int? = null,
        @Json(name = "webformatURL") var webformatURL: String? = null,
        @Json(name = "webformatWidth") var webformatWidth: Int? = null,
        @Json(name = "webformatHeight") var webformatHeight: Int? = null,
        @Json(name = "largeImageURL") var largeImageURL: String? = null,
        @Json(name = "imageWidth") var imageWidth: Int? = null,
        @Json(name = "imageHeight") var imageHeight: Int? = null,
        @Json(name = "imageSize") var imageSize: Int? = null,
        @Json(name = "views") var views: Int? = null,
        @Json(name = "downloads") var downloads: Int? = null,
        @Json(name = "collections") var collections: Int? = null,
        @Json(name = "likes") var likes: Int? = null,
        @Json(name = "comments") var comments: Int? = null,
        @Json(name = "user_id") var userId: Int? = null,
        @Json(name = "user") var user: String? = null,
        @Json(name = "userImageURL") var userImageURL: String? = null

    )
}