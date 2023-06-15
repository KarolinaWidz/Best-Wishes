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
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import edu.karolinawidz.bestwishes.enums.CardType
import edu.karolinawidz.bestwishes.ui.PictureListFragment
import edu.karolinawidz.bestwishes.viewModel.CardViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class PictureListFragmentTests {

    private lateinit var navController: TestNavHostController
    private lateinit var pictureListFragmentScenario: FragmentScenario<PictureListFragment>
    private val cardViewModel = CardViewModel()

    @Before
    fun setup() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        pictureListFragmentScenario =
            launchFragmentInContainer(themeResId = R.style.Theme_BestWishes)
        pictureListFragmentScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(destId = R.id.pictureListFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        cardViewModel.setCardType(CardType.BIRTHDAY)
    }

    @Test
    fun check_if_picture_chosen_after_click() {
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