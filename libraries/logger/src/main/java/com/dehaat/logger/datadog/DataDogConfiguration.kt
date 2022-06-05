package com.dehaat.logger.datadog

import com.dehaat.logger.Configuration

class DataDogConfiguration(
    private val logsEnabled: Boolean = false,
    private val tracesEnabled: Boolean = false,
    private val crashReportsEnabled: Boolean = false,
    private val rumEnabled: Boolean = false
) : Configuration {
    override fun buildConfiguration(): com.datadog.android.core.configuration.Configuration {
        return com.datadog.android.core.configuration.Configuration.Builder(
            logsEnabled = logsEnabled,
            tracesEnabled = tracesEnabled,
            rumEnabled = crashReportsEnabled,
            crashReportsEnabled = rumEnabled
        )
            .trackInteractions()
            .useEUEndpoints()
            .build()

    }
}