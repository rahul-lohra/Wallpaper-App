package com.data.repository

import com.data.ApiResult
import com.data.ApiResultFail
import com.data.ApiResultSuccess


interface BaseRepository {
//    suspend fun getPullRequest(requestParams: GithubRequestParams): ApiResult<List<PullRequestResult>>

    suspend fun <T> invoke(method: suspend () -> T): ApiResult<T> {
        try {
            return ApiResultSuccess(method.invoke())
        } catch (th: Throwable) {
            return ApiResultFail(th)
        }
    }
}