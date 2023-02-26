package edu.karolinawidz.bestwishes

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import edu.karolinawidz.bestwishes.ui.PictureListFragment
import edu.karolinawidz.bestwishes.util.ClickInsideItem
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NavigationTests {

    private lateinit var navController: TestNavHostController
    private lateinit var pictureListScenario: FragmentScenario<PictureListFragment>

    @Before
    fun setup() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        pictureListScenario =
            launchFragmentInContainer(themeResId = R.style.Theme_BestWishes)
        pictureListScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @Test
    fun navigate_to_words_nav_component() {
        onView(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    9, ClickInsideItem.clickChildWithId(R.id.picture_radio_button)
                )
            )
        onView(withId(R.id.start_over_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.wishFragment)
    }
}