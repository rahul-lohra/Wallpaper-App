package com.dehaat.logger.datadog.configuration

import com.datadog.android.DatadogSite
import com.datadog.android.rum.tracking.ActivityViewTrackingStrategy

class DataDogConfiguration(
    private val logsEnabled: Boolean = false,
    private val tracesEnabled: Boolean = false,
    private val crashReportsEnabled: Boolean = false,
    private val rumEnabled: Boolean = false,
    private val dataDogSite: DatadogSite = DatadogSite.US5
) : Configuration {
    override fun buildConfiguration(): com.datadog.android.core.configuration.Configuration {
        return com.datadog.android.core.configuration.Configuration.Builder(
            logsEnabled = logsEnabled,
            tracesEnabled = tracesEnabled,
            rumEnabled = crashReportsEnabled,
            crashReportsEnabled = rumEnabled
        )
            .trackInteractions()
            .trackLongTasks()
            .useViewTrackingStrategy(ActivityViewTrackingStrategy(true))
            .useSite(dataDogSite)
            .build()

    }
}