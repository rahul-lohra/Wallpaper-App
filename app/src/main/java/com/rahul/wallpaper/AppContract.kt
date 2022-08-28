package com.rahul.wallpaper

import android.content.Context

interface AppContract {

    fun provideAppContext(): Context

    companion object {
        var instance: AppContract? = null
    }
}