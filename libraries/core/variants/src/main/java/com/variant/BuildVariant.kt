package com.variant

object BuildVariant {
    private var appBuildVariant: Variant? = null

    fun isDebug(): Boolean =
        if (appBuildVariant == null) throw Exception("Variant Not Set") else appBuildVariant == Variant.DEBUG

    fun setBuildVariant(variant: Variant) {
        if (appBuildVariant == null) {
            appBuildVariant = variant
        }
    }
}

enum class Variant {
    RELEASE, DEBUG
}