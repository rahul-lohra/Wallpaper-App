package com.dehaat.logger

import com.datadog.android.core.configuration.Credentials

interface Credential {
    fun setupCredential(): Credentials
}