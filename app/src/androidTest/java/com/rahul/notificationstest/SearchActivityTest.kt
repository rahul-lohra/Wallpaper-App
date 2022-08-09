package com.rahul.notificationstest

import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.textview.MaterialTextView
import com.rahul.notificationstest.feature.search.ui.activities.SearchActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchActivityTest {

    @get:Rule
    val rule = ActivityScenarioRule(SearchActivity::class.java)

    @Test
    fun test_search_result_is_appearing(){

        Espresso.onView(ViewMatchers.withId(R.id.search_bar))
            .perform(ViewActions.typeText("S"))

        val assertion = ViewAssertion { view, noViewFoundException ->
            val text = (((view as RecyclerView).getChildAt(0) as LinearLayout).getChildAt(0) as MaterialTextView).text
            assert(text == "Sunday")
        }

        Espresso.onView(ViewMatchers.withId(R.id.recycler_view))
//            .check(ViewAssertions.matches(ViewMatchers.hasChildCount(2)))
            .check(assertion)

    }
}