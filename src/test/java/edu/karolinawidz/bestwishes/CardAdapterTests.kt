package edu.karolinawidz.bestwishes

import android.content.Context
import androidx.constraintlayout.utils.widget.MockView
import edu.karolinawidz.bestwishes.adapter.ItemAdapter
import edu.karolinawidz.bestwishes.model.Wish
import org.junit.Assert.assertEquals
import org.junit.Test

import org.mockito.Mockito.mock

class CardAdapterTests {

    private val context = mock(Context::class.java)

    @Test
    fun adapter_size() {
        val data = listOf(
            Wish(R.string.birthday1, R.drawable.birthday1),
            Wish(R.string.birthday2, R.drawable.birthday2),
            Wish(R.string.birthday3, R.drawable.birthday3)
        )

        assertEquals(data.size, ItemAdapter(context, data).itemCount)
    }
}