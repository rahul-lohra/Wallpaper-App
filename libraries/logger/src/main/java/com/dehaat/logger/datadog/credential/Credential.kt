package com.dehaat.logger.datadog.credential

import com.datadog.android.core.configuration.Credentials

interface Credential {
    fun setupCredential(): Credentials
}