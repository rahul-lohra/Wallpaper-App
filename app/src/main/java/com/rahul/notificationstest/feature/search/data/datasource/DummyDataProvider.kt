package com.rahul.notificationstest.feature.search.data.datasource

import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class DummyDataProvider @Inject constructor() {

    suspend fun getData() = flowOf(
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday"
    )
}