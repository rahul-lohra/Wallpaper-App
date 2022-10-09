package com.logger

import ApiRequestData
import LogData
import com.logger.data.Priority
import com.logger.data.Tag

object ServerLogger : ServerLoggerContract {

    var logger: ServerLoggerContract? = LocalLogger()

    override fun d(@Priority priority: String, logData: LogData, throwable: Throwable?) {
        logger?.d(priority, logData, throwable)
    }

    override fun e(@Priority priority: String, logData: LogData, throwable: Throwable?) {
        logger?.e(priority, logData, throwable)
    }

    override fun apiRequestError(apiRequestData: ApiRequestData, throwable: Throwable?) {
        logger?.apiRequestError(apiRequestData, throwable)
    }
}