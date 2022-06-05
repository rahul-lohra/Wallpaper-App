package com.dehaat.logger

import android.content.Context

internal interface Initializer {
    fun init(context: Context, appData: AppData): ServerLoggerContract
}