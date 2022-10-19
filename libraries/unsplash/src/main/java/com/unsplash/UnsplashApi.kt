package com.unsplash

import androidx.annotation.StringDef
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.unsplash.UnsplashApi.Config.ADDRESS_KEY
import com.unsplash.UnsplashApi.Config.AUTH_TOKEN_URL
import com.unsplash.UnsplashApi.Config.REDIRECT_URI
import com.unsplash.data.UnsplashUserData
import retrofit2.http.*

interface UnsplashApi {
    object Config {

        const val ADDRESS_KEY = BuildConfig.UNSPLASH_API_KEY
        internal const val SECRET_KEY = BuildConfig.UNSPLASH_SECRET_KEY
        const val BASE_URL = "https://api.unsplash.com"
        const val AUTH_TOKEN_URL = "https://unsplash.com/oauth/token"
        const val REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob"
        const val LOGIN_URL = "https://unsplash.com/oauth/authorize"
    }

    object QueryParams {
        const val PAGE = "page"
        const val PER_PAGE = "per_page"
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
    suspend fun getPhotos(
        @Query(QueryParams.PAGE) pageNumber: Int,
        @Query(QueryParams.PER_PAGE) perPage: Int
    ): List<UnsplashResponse>

    @POST
    suspend fun getAuthToken(
        @Body body: AuthTokenRequestBody,
        @Url url: String = AUTH_TOKEN_URL,
    ): AuthTokenResponse

    @GET("/me")
    suspend fun getUserData(): UnsplashUserData

    @GET("/users/{username}")
    suspend fun getUserData(@Path("username") userName: String): UnsplashUserData

    @GET("/users/{username}/following")
    suspend fun getFollowing(@Path("username") userName: String): List<FollowingEntity>
}

@JsonClass(generateAdapter = true)
data class AuthTokenRequestBody(
    @Json(name = "code") val code: String,
    @Json(name = "client_id") var clientId: String = ADDRESS_KEY,
    @Json(name = "client_secret") var clientSecret: String = UnsplashApi.Config.SECRET_KEY,
    @Json(name = "redirect_uri") var redirectUri: String = REDIRECT_URI,
    @Json(name = "grant_type") var grantType: String = "authorization_code"
)

@JsonClass(generateAdapter = true)
data class AuthTokenResponse(
    @Json(name = "access_token") val accessToken: String,
    @Json(name = "refresh_token") val refreshToken: String,
    @Json(name = "token_type") var tokenType: String = ADDRESS_KEY,
    @Json(name = "scope") var scope: String = UnsplashApi.Config.SECRET_KEY,
    @Json(name = "created_at") var createdAt: String = REDIRECT_URI,
)
