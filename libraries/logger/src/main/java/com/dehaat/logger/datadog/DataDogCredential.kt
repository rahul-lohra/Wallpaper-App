package com.dehaat.logger.datadog

import com.datadog.android.core.configuration.Credentials
import com.dehaat.logger.Credential

class DataDogCredential(
    private val clientToken: String,
    private val environmentName: String,
    private val buildType: String,
    private val rumAppId: String?
) : Credential {
    override fun setupCredential(): Credentials {
        return Credentials(
            clientToken,
            environmentName,
            buildType,
            rumAppId
        )
    }
}