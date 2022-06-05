package com.dehaat.logger

import com.datadog.android.core.configuration.Configuration

interface Configuration {
    fun buildConfiguration(): Configuration
}