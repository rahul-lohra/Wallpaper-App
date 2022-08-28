package com.rahul.wallpaper.data

sealed class ApiResult<out T>
class ApiResultSuccess<T>(t: T) : ApiResult<T>()
class ApiResultFail<T>(ex: Exception) : ApiResult<T>()