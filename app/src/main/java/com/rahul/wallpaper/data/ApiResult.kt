package com.rahul.wallpaper.data

sealed class ApiResult<T>
class ApiResultSuccess<T>(val data: T) : ApiResult<T>()
class ApiResultFail<T>(val ex: Exception) : ApiResult<T>()