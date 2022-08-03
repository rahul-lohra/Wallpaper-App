package com.dehaat.logger.datadog.initializer

import android.content.Context
import com.dehaat.logger.data.AppData
import com.dehaat.logger.ServerLoggerContract

internal interface Initializer {
    fun init(context: Context, appData: AppData): ServerLoggerContract
}