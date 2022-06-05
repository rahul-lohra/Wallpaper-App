package com.dehaat.logger

import android.content.Context
import com.dehaat.logger.datadog.DataDogConfiguration
import com.dehaat.logger.datadog.DataDogCredential
import com.dehaat.logger.datadog.DataDogInitializer

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