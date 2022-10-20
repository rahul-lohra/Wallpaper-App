package com.unsplash.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.unsplash.UnsplashProfileImage

@JsonClass(generateAdapter = true)
data class UnsplashUserData(
    @Json(name = "id") val id: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "username") val username: String,
    @Json(name = "name") val name: String,
    @Json(name = "profile_image") val profileImage: UnsplashProfileImage,
    @Json(name = "followers_count") val followersCount: Int,
    @Json(name = "following_count") val followingCount: Int,
    @Json(name = "instagram_username") val instagramUsername: String?,
    @Json(name = "twitter_username") val twitterUsername: String?,
)
