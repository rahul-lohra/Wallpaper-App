package com.dehaat.logger

import androidx.annotation.StringDef

@StringDef(Priority.P1, Priority.P2, Priority.P3)
@Retention
annotation class Priority {
    companion object {
        const val P1 = "P1"
        const val P2 = "P2"
        const val P3 = "P3"
    }
}