package com.dehaat.logger.datadog

import android.content.Context
import android.util.Log
import com.datadog.android.BuildConfig
import com.datadog.android.Datadog
import com.datadog.android.core.configuration.Configuration
import com.datadog.android.core.configuration.Credentials
import com.datadog.android.log.Logger
import com.datadog.android.privacy.TrackingConsent
import com.datadog.android.rum.GlobalRum
import com.datadog.android.rum.RumMonitor
import com.dehaat.logger.AppData
import com.dehaat.logger.Initializer
import com.dehaat.logger.ServerLoggerContract

internal class DataDogInitializer(val configuration: Configuration, val credential: Credentials) :
    Initializer {
    val TAG = "DataDogInitializer"

    override fun init(context: Context, appData: AppData):ServerLoggerContract {
        Datadog.initialize(context, credential, configuration, TrackingConsent.GRANTED)
        GlobalRum.registerIfAbsent(RumMonitor.Builder().build())
        return prepareLogger(context.applicationContext.packageName, appData)
    }

    private fun prepareLogger(packageName:String, appData:AppData): ServerLoggerContract {

        if (Datadog.isInitialized()) {
            val log = Logger.Builder()
                .setServiceName(packageName)
                .setNetworkInfoEnabled(true)
                .setLogcatLogsEnabled(true)
//                .setDatadogLogsEnabled(!BuildConfig.DEBUG)
                .setDatadogLogsEnabled(true)
                .setBundleWithTraceEnabled(true)
                .setLoggerName(appData.loggerName)
                .build()
            log.addAttribute("version_name", appData.versionName)
            log.addAttribute("version_code", appData.versionCode)
            return DataDogLogger(log)
        } else {
            Log.d(TAG, "Data dog not initialised")
            return DataDogLogger(null)
        }
    }
}