package edu.karolinawidz.bestwishes

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import edu.karolinawidz.bestwishes.ui.MenuFragment
import edu.karolinawidz.bestwishes.ui.PictureListFragment
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTests {

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun navigate_from_menu_to_picture_list() {
        val menuScenario =
            launchFragmentInContainer<MenuFragment>(themeResId = R.style.Theme_BestWishes)
        menuScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.birthday_card_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.pictureListFragment)
    }

    @Test
    fun navigate_from_picture_list_to_wish_list() {
        val pictureListScenario =
            launchFragmentInContainer<PictureListFragment>(themeResId = R.style.Theme_BestWishes)
        pictureListScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(destId = R.id.pictureListFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.next_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.wishFragment)
    }
}