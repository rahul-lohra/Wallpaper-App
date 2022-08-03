package com.dehaat.logger

import com.dehaat.logger.data.Priority
import com.dehaat.logger.data.Tag

object ServerLogger {
    internal var logger: ServerLoggerContract? = null

    fun d(@Priority priority: String, tag: Tag, message: String, throwable: Throwable? = null) {
        logger?.d(priority, tag, message, throwable)
    }

    fun e(@Priority priority: String, tag: Tag, message: String, throwable: Throwable? = null) {
        logger?.e(priority, tag, message, throwable)
    }

}