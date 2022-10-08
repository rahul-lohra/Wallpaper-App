package com.logger

import ApiRequestData
import LogData
import com.logger.data.Priority
import com.logger.data.Tag

interface ServerLoggerContract {
    fun d(@Priority priority: String, logData: LogData, throwable: Throwable? = null)
    fun e(@Priority priority: String, logData: LogData, throwable: Throwable? = null)
    fun apiRequestError(apiRequestData: ApiRequestData, throwable: Throwable? = null)

    fun getFormattedMessage(@Priority priority: String, message: String): String {
        return "$priority: $message"
    }
}