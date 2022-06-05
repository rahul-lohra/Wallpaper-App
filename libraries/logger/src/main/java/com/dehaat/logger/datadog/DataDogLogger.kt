package com.dehaat.logger.datadog

import android.util.Log
import com.datadog.android.Datadog
import com.datadog.android.log.Logger
import com.dehaat.logger.Priority
import com.dehaat.logger.ServerLoggerContract
import com.dehaat.logger.Tag

internal class DataDogLogger(private val log: Logger?) : ServerLoggerContract {
    private val separator = "-"

    override fun d(
        @Priority priority: String,
        tag: Tag,
        reason: String,
        throwable: Throwable?
    ) {
        if (Datadog.isInitialized()) {
//            val map = mapOf("reason" to reason)
//            log?.d(
//                message = "$priority$separator$tag",
//                throwable = throwable,
//                attributes = map
//            )
//
//            log?.e(
//                message = "$priority$separator$tag",
//                throwable = throwable,
//                attributes = map
//            )
//            log?.i(
//                message = "$priority$separator$tag",
//                throwable = throwable,
//                attributes = map
//            )
            log?.d("Hello world")
            Datadog.setVerbosity(android.util.Log.VERBOSE)
        } else {
            Log.d("dehaat-partner", "Data dog not initialised")
        }
    }

    override fun e(
        @Priority priority: String,
        tag: Tag,
        reason: String,
        throwable: Throwable?
    ) {
        if (Datadog.isInitialized()) {
//            val map = mapOf("reason" to reason)
//            log?.d(
//                message = "$priority$separator$tag",
//                throwable = throwable,
//                attributes = map
//            )
//
//            log?.e(
//                message = "$priority$separator$tag",
//                throwable = throwable,
//                attributes = map
//            )
//            log?.i(
//                message = "$priority$separator$tag",
//                throwable = throwable,
//                attributes = map
//            )
            log?.d("Hello world")
            Datadog.setVerbosity(android.util.Log.VERBOSE)
        } else {
            Log.d("dehaat-partner", "Data dog not initialised")
        }
    }

}