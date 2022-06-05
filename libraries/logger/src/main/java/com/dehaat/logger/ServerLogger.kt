package com.dehaat.logger

object ServerLogger {
    internal var logger: ServerLoggerContract? = null

    fun d(@Priority priority: String, tag: Tag, message: String, throwable: Throwable? = null) {
        logger?.d(priority, tag, message, throwable)
    }

    fun e(@Priority priority: String, tag: Tag, message: String, throwable: Throwable? = null) {
        logger?.e(priority, tag, message, throwable)
    }

}