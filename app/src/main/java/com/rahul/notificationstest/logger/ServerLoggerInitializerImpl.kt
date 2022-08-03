package com.rahul.notificationstest.logger

import android.content.Context
import com.datadog.android.DatadogSite
import com.dehaat.logger.data.AppData
import com.dehaat.logger.datadog.LoggerBuilder
import com.dehaat.logger.ServerLoggerInitializerContract
import com.dehaat.logger.datadog.configuration.DataDogConfiguration
import com.dehaat.logger.datadog.credential.DataDogCredential


class ServerLoggerInitializerImpl(private val context: Context) : ServerLoggerInitializerContract {

    override fun initialize() {
        val configuration = getDatadogConfiguration()
        val credentials = getDatadogCredentials()
        val loggerName = "notification-test-name"
        LoggerBuilder().initialise(
            context,
            AppData(
                "BuildConfig.VERSION_NAME",
                1,
                loggerName
            ),
            configuration,
            credentials
        )
    }

    private fun getDatadogCredentials() = DataDogCredential(
        "pub76546afbb816e7a1413fa86d8913697d",
        "prod",
        "BuildConfig.BUILD_TYPE",
        "19ed2517-82aa-4d5c-a4d0-2a244d517176"
    )

    private fun getDatadogConfiguration() = DataDogConfiguration(
        logsEnabled = true,
        tracesEnabled = true,
        crashReportsEnabled = true,
        rumEnabled = true,
        dataDogSite = DatadogSite.US5
    )
}