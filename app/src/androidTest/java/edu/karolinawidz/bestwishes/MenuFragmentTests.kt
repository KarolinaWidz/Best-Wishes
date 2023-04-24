package edu.karolinawidz.bestwishes

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import edu.karolinawidz.bestwishes.ui.MenuFragment
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MenuFragmentTests {

    private lateinit var navController: TestNavHostController
    private lateinit var menuFragmentFragmentScenario: FragmentScenario<MenuFragment>

    @Before
    fun setup() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        menuFragmentFragmentScenario =
            launchFragmentInContainer(themeResId = R.style.Theme_BestWishes)
        menuFragmentFragmentScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @Test
    fun navigate_to_picture_fragment_anniversary() {
        onView(withId(R.id.anniversary_card_button))
            .perform(
                click()
            )
        assertEquals(navController.currentDestination?.id, R.id.pictureListFragment)
    }

    @Test
    fun navigate_to_picture_fragment_birthday() {
        onView(withId(R.id.birthday_card_button))
            .perform(
                click()
            )
        assertEquals(navController.currentDestination?.id, R.id.pictureListFragment)
    }


}