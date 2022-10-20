package com.login.domain.usecase

import com.data.ApiResultSuccess
import com.login.data.repository.LoginRepository
import com.login.domain.models.LoginDomainState
import com.login.domain.models.LoginDomainStateSuccess
import com.squareup.moshi.Moshi
import com.unsplash.AuthTokenResponse
import com.unsplash.data.UnsplashUserData
import com.unsplash.domain.UnsplashUserUseCase
import okhttp3.HttpUrl
import javax.inject.Inject

class MockUnsplashLoginUseCase @Inject constructor(
    private val repository: LoginRepository,
    private val unsplashUserUseCase: UnsplashUserUseCase
) :
    UnsplashLoginContract {
    override fun getLoginUri(): String {
        return HttpUrl.Builder().scheme("https").host("unsplash.com")
            .build().toString()
    }

    override suspend fun performLogin(url: String): LoginDomainState {
        val response = AuthTokenResponse(
            "IJIkt8bFt_bTwt2jLxRlvj2c44GmMnz3p1Eoc93treo",
            "fake token",
            "bearer",
            "public read",
            "2022-10-14T13:43:51Z"
        )
        repository.saveAuthTokenResponse(response)
        val moshi = Moshi.Builder().build()
        val unsplashUserDataAdapter = moshi.adapter(UnsplashUserData::class.java)
        val selfUserData = ApiResultSuccess(unsplashUserDataAdapter.fromJson(mockkedMe)!!)
        repository.saveUserData(selfUserData.data)
        return LoginDomainStateSuccess
    }
}

val mockkedMe = """
    {
        "id": "fqsHga57HUQ",
        "updated_at": "2022-10-14T13:43:51Z",
        "username": "rahullohra",
        "name": "Rahul Lohra",
        "first_name": "Rahul",
        "last_name": "Lohra",
        "twitter_username": null,
        "portfolio_url": null,
        "bio": null,
        "location": null,
        "links": {
            "self": "https://api.unsplash.com/users/rahullohra",
            "html": "https://unsplash.com/@rahullohra",
            "photos": "https://api.unsplash.com/users/rahullohra/photos",
            "likes": "https://api.unsplash.com/users/rahullohra/likes",
            "portfolio": "https://api.unsplash.com/users/rahullohra/portfolio",
            "following": "https://api.unsplash.com/users/rahullohra/following",
            "followers": "https://api.unsplash.com/users/rahullohra/followers"
        },
        "profile_image": {
            "small": "https://images.unsplash.com/profile-fb-1659901147-8e1c0657579e.jpg?ixlib=rb-4.0.3&crop=faces&fit=crop&w=32&h=32",
            "medium": "https://images.unsplash.com/profile-fb-1659901147-8e1c0657579e.jpg?ixlib=rb-4.0.3&crop=faces&fit=crop&w=64&h=64",
            "large": "https://images.unsplash.com/profile-fb-1659901147-8e1c0657579e.jpg?ixlib=rb-4.0.3&crop=faces&fit=crop&w=128&h=128"
        },
        "instagram_username": null,
        "total_collections": 0,
        "total_likes": 8,
        "total_photos": 0,
        "accepted_tos": false,
        "for_hire": false,
        "social": {
            "instagram_username": null,
            "portfolio_url": null,
            "twitter_username": null,
            "paypal_email": null
        },
        "followed_by_user": false,
        "photos": [],
        "badge": null,
        "tags": {
            "custom": [],
            "aggregated": []
        },
        "followers_count": 0,
        "following_count": 6,
        "allow_messages": true,
        "numeric_id": 10215709,
        "downloads": 0,
        "meta": {
            "index": false
        },
        "uid": "fqsHga57HUQ",
        "confirmed": true
    }
""".trimIndent()