package com.dehaat.logger.datadog.configuration

import com.datadog.android.core.configuration.Configuration

interface Configuration {
    fun buildConfiguration(): Configuration
}