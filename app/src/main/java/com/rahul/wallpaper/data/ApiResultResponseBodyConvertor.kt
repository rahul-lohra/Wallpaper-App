package com.rahul.wallpaper.data

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import okhttp3.ResponseBody
import okio.ByteString.Companion.decodeHex
import retrofit2.Converter
import java.io.IOException

class ApiResultResponseBodyConvertor<T>(val adapter: JsonAdapter<T>) :
    Converter<ResponseBody, ApiResult<T>> {
    companion object {
        private val UTF8_BOM = "EFBBBF".decodeHex()
    }

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): ApiResult<T> {
        val source = value.source()
        return try {
            // Moshi has no document-level API so the responsibility of BOM skipping falls to whatever
            // is delegating to it. Since it's a UTF-8-only library as well we only honor the UTF-8 BOM.
            if (source.rangeEquals(0, UTF8_BOM)) {
                source.skip(UTF8_BOM.size.toLong())
            }
            val reader = JsonReader.of(source)
            val result: T? = adapter.fromJson(reader)
            if (reader.peek() != JsonReader.Token.END_DOCUMENT) {
                ApiResultFail(JsonDataException("JSON document was not fully consumed."))
            } else {
                if (result != null) {
                    ApiResultSuccess(result)
                } else {
                    ApiResultFail(Exception("Result is Null"))
                }
            }
        } finally {
            value.close()
        }
    }

//    fun ResponseBody.toApiResult(): ApiResult<T> {
//        return try {
//            ApiResultSuccess()
//        } catch (ex: Exception) {
//            ApiResultFail(ex)
//        }
//    }
}
