package edu.karolinawidz.bestwishes

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.MediumTest
import edu.karolinawidz.bestwishes.enum.CardType
import edu.karolinawidz.bestwishes.ui.PictureListFragment
import edu.karolinawidz.bestwishes.viewModel.CardViewModel
import org.junit.Before
import org.junit.Test

@MediumTest
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
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        cardViewModel.setCardType(CardType.BIRTHDAY)
    }

    @Test
    fun navigate_to_wish_fragment() {
//        onView(withId(R.id.recycler_view))
//            .perform(
//                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                    9, ClickInsideItem.clickChildWithId(R.id.picture_radio_button)
//                )
//            )
//        onView(withId(R.id.next_button)).perform(click())
//        assertEquals(navController.currentDestination?.id, R.id.wishFragment)
    }
}