package com.rahul.notificationstest.feature.search.data.apis.unsplash

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UnsplashResponse(
    @Json(name = "id") var id: String? = null,
    @Json(name = "created_at") var createdAt: String? = null,
    @Json(name = "updated_at") var updatedAt: String? = null,
    @Json(name = "promoted_at") var promotedAt: String? = null,
    @Json(name = "width") var width: Int? = null,
    @Json(name = "height") var height: Int? = null,
    @Json(name = "color") var color: String? = null,
    @Json(name = "blur_hash") var blurHash: String? = null,
    @Json(name = "description") var description: String? = null,
    @Json(name = "alt_description") var altDescription: String? = null,
    @Json(name = "urls") var urls: Urls? = Urls(),
    @Json(name = "links") var links: UnsplashLinks? = UnsplashLinks(),
    @Json(name = "categories") var categories: List<String> = arrayListOf(),
    @Json(name = "likes") var likes: Int? = null,
    @Json(name = "liked_by_user") var likedByUser: Boolean? = null,
    @Json(name = "current_user_collections") var currentUserCollections: List<String> = arrayListOf(),
    @Json(name = "sponsorship") var sponsorship: Sponsorship? = Sponsorship(),
    @Json(name = "user") var user: UnsplashUser? = UnsplashUser()

) {
    @JsonClass(generateAdapter = true)
    data class Urls(

        @Json(name = "raw") var raw: String? = null,
        @Json(name = "full") var full: String? = null,
        @Json(name = "regular") var regular: String? = null,
        @Json(name = "small") var small: String? = null,
        @Json(name = "thumb") var thumb: String? = null,
        @Json(name = "small_s3") var smallS3: String? = null

    )
    @JsonClass(generateAdapter = true)
    data class UnsplashLinks(

        @Json(name = "self") var self: String? = null,
        @Json(name = "html") var html: String? = null,
        @Json(name = "download") var download: String? = null,
        @Json(name = "download_location") var downloadLocation: String? = null
    )

    @JsonClass(generateAdapter = true)
    data class UnsplashProfileImage(

        @Json(name = "small") var small: String? = null,
        @Json(name = "medium") var medium: String? = null,
        @Json(name = "large") var large: String? = null

    )

    @JsonClass(generateAdapter = true)
    data class Social(

        @Json(name = "instagram_username") var instagramUsername: String? = null,
        @Json(name = "portfolio_url") var portfolioUrl: String? = null,
        @Json(name = "twitter_username") var twitterUsername: String? = null,
        @Json(name = "paypal_email") var paypalEmail: String? = null

    )

    @JsonClass(generateAdapter = true)
    data class Sponsorship(

        @Json(name = "impression_urls") var impressionUrls: List<String> = arrayListOf(),
        @Json(name = "tagline") var tagline: String? = null,
        @Json(name = "tagline_url") var taglineUrl: String? = null,
        @Json(name = "sponsor") var user: UnsplashUser? = UnsplashUser()

    )

    @JsonClass(generateAdapter = true)
    data class Links(

        @Json(name = "self") var self: String? = null,
        @Json(name = "html") var html: String? = null,
        @Json(name = "photos") var photos: String? = null,
        @Json(name = "likes") var likes: String? = null,
        @Json(name = "portfolio") var portfolio: String? = null,
        @Json(name = "following") var following: String? = null,
        @Json(name = "followers") var followers: String? = null

    )
    @JsonClass(generateAdapter = true)
    data class UnsplashUser(

        @Json(name = "id") var id: String? = null,
        @Json(name = "updated_at") var updatedAt: String? = null,
        @Json(name = "username") var username: String? = null,
        @Json(name = "name") var name: String? = null,
        @Json(name = "first_name") var firstName: String? = null,
        @Json(name = "last_name") var lastName: String? = null,
        @Json(name = "twitter_username") var twitterUsername: String? = null,
        @Json(name = "portfolio_url") var portfolioUrl: String? = null,
        @Json(name = "bio") var bio: String? = null,
        @Json(name = "location") var location: String? = null,
        @Json(name = "links") var links: Links? = Links(),
        @Json(name = "profile_image") var profileImage: UnsplashProfileImage? = UnsplashProfileImage(),
        @Json(name = "instagram_username") var instagramUsername: String? = null,
        @Json(name = "total_collections") var totalCollections: Int? = null,
        @Json(name = "total_likes") var totalLikes: Int? = null,
        @Json(name = "total_photos") var totalPhotos: Int? = null,
        @Json(name = "accepted_tos") var acceptedTos: Boolean? = null,
        @Json(name = "for_hire") var forHire: Boolean? = null,
        @Json(name = "social") var social: Social? = Social()

    )
}