package com.rahul.wallpaper.feature.search.data.datasource

import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class DummyDataProvider @Inject constructor() {

    fun getData() = flowOf(
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday"
    )

    fun getDataArrayList() = arrayListOf(
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday"
    )
    fun getTabViewItems() = arrayListOf(
        "Editorial",
        "Following",
        "-",
        "Nature",
        "Architecture",
        "Travel",
        "Friday",
        "Saturday"
    )
}