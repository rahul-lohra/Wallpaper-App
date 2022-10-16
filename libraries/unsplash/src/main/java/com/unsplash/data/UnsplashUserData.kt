package com.unsplash.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UnsplashUserData(
    @Json(name = "id") val id: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "username") val username: String,
    @Json(name = "name") val name: String,
    @Json(name = "profile_image") val profileImage: ProfileImage,
    @Json(name = "followers_count") val followersCount: Int,
    @Json(name = "following_count") val followingCount: Int,
    @Json(name = "instagram_username") val instagramUsername: String,
    @Json(name = "twitter_username") val twitterUsername: String,
) {

    data class ProfileImage(
        @Json(name = "small") val small: String,
        @Json(name = "medium") val medium: String,
        @Json(name = "large") val large: String,
    )

    data class Links(
        @Json(name = "photos") val photos: String,
        @Json(name = "likes") val likes: String,
    )

}

