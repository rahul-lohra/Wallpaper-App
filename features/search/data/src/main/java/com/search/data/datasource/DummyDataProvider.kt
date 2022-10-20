package com.search.data.datasource

import javax.inject.Inject

class DummyDataProvider @Inject constructor() {

    fun getTabViewItems() = arrayListOf(
        "Editorial",
        "Following",
        "Nature",
        "Architecture",
        "Travel",
        "Friday",
        "Saturday"
    )
}