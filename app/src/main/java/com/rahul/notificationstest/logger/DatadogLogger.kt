package com.rahul.notificationstest.logger

import android.content.Context
import com.datadog.android.Datadog
import com.datadog.android.DatadogSite
import com.datadog.android.core.configuration.Configuration
import com.datadog.android.core.configuration.Credentials
import com.datadog.android.privacy.TrackingConsent
import com.datadog.android.rum.GlobalRum
import com.datadog.android.rum.RumMonitor
import com.datadog.android.rum.tracking.ActivityViewTrackingStrategy
import com.datadog.android.rum.tracking.ViewTrackingStrategy

class DatadogLogger {
    fun setupDatadog(context: Context) {

        val clientToken = "pub76546afbb816e7a1413fa86d8913697d"
        val applicationId = "19ed2517-82aa-4d5c-a4d0-2a244d517176"

        val environmentName = "debug"
        val appVariantName = "first-variant"

        val configuration = Configuration.Builder(
            rumEnabled = true,
            crashReportsEnabled = true,
            logsEnabled = true,
            tracesEnabled = true
        )
            .trackInteractions()
            .trackLongTasks()
            .useViewTrackingStrategy(ActivityViewTrackingStrategy(true))
            .useSite(DatadogSite.US5)
            .build()
        val credentials = Credentials(clientToken, environmentName, appVariantName, applicationId)
        Datadog.initialize(context, credentials, configuration, TrackingConsent.GRANTED)
        GlobalRum.registerIfAbsent(RumMonitor.Builder().build())
    }
}