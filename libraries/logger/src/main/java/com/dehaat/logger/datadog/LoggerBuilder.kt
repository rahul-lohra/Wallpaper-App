package com.dehaat.logger.datadog

import android.content.Context
import com.dehaat.logger.ServerLogger
import com.dehaat.logger.data.AppData
import com.dehaat.logger.datadog.configuration.DataDogConfiguration
import com.dehaat.logger.datadog.credential.DataDogCredential
import com.dehaat.logger.datadog.initializer.DataDogInitializer

class LoggerBuilder {

    fun initialise(
        context: Context,
        appData: AppData,
        configuration: DataDogConfiguration,
        credential: DataDogCredential
    ) {
        val dataDogInitializer =
            DataDogInitializer(configuration.buildConfiguration(), credential.setupCredential())
        ServerLogger.logger = dataDogInitializer.init(context, appData)
    }
}