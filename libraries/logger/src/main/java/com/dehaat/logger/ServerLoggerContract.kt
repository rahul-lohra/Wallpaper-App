package com.dehaat.logger

interface ServerLoggerContract {
    fun d(@Priority priority: String, tag: Tag, message:String, throwable: Throwable?= null)
    fun e(@Priority priority: String, tag: Tag, message:String, throwable: Throwable?= null)
}