package edu.karolinawidz.bestwishes

import android.content.Context
import edu.karolinawidz.bestwishes.adapter.PictureItemAdapter
import edu.karolinawidz.bestwishes.model.Picture
import org.junit.Assert.assertEquals
import org.junit.Test

import org.mockito.Mockito.mock

class CardAdapterTests {

    private val context = mock(Context::class.java)

    @Test
    fun adapter_size() {
        val data = listOf(
            Picture(R.string.birthday1, R.drawable.birthday1),
            Picture(R.string.birthday2, R.drawable.birthday2),
            Picture(R.string.birthday3, R.drawable.birthday3)
        )

        assertEquals(data.size, PictureItemAdapter(context, data).itemCount)
    }
}