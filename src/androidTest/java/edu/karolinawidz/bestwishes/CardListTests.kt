package edu.karolinawidz.bestwishes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import edu.karolinawidz.bestwishes.adapter.ItemAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CardListTests {

    @get:Rule
    val activity = ActivityScenarioRule(PictureActivity::class.java)

    @Test
    fun scroll_to_last(){
        val lastItemPosition = 13
        onView(withId(R.id.recycler_view))
            .perform(RecyclerViewActions.scrollToPosition<ItemAdapter.ItemViewHolder>(lastItemPosition))
        onView(withId(R.id.recycler_view))
            .check(matches(isDisplayed()))
    }
}