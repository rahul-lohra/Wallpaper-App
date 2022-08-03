package com.dehaat.logger

import com.dehaat.logger.data.Priority
import com.dehaat.logger.data.Tag

interface ServerLoggerContract {
    fun d(@Priority priority: String, tag: Tag, message:String, throwable: Throwable?= null)
    fun e(@Priority priority: String, tag: Tag, message:String, throwable: Throwable?= null)
}