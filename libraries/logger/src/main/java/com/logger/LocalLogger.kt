package com.logger

import ApiRequestData
import LogData
import android.util.Log
import com.logger.data.Priority

class LocalLogger : ServerLoggerContract {
    override fun d(@Priority priority: String, logData: LogData, throwable: Throwable?) {
        Log.d(logData.tag, getFormattedMessage(priority, logData.message))
    }

    override fun e(@Priority priority: String, logData: LogData, throwable: Throwable?) {
        Log.e(logData.tag, getFormattedMessage(priority, logData.message), throwable)
    }

    override fun apiRequestError(apiRequestData: ApiRequestData, throwable: Throwable?) {
        Log.e("ApiRequestError", apiRequestData.toString(), throwable)
    }
}