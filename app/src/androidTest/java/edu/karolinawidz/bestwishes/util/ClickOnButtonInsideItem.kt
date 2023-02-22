package edu.karolinawidz.bestwishes.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher


class ClickInsideItem {

    companion object {
        fun clickChildWithId(id: Int): ViewAction {
            return object : ViewAction {
                override fun getDescription(): String {
                    return "Click on child with specified ID"
                }

                override fun getConstraints(): Matcher<View>? {
                    return ViewMatchers.isAssignableFrom(RecyclerView::class.java)
                }

                override fun perform(uiController: UiController?, view: View?) {
                    val v: View = view!!.findViewById(id)
                    v.performClick()
                }

            }
        }
    }
}