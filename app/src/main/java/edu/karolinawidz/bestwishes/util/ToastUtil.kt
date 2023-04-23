package edu.karolinawidz.bestwishes.util

import android.content.Context
import android.view.Gravity
import android.widget.Toast

object ToastUtil {
    private const val TOAST_OFFSET_X = 0
    private const val TOAST_OFFSET_Y = 220

    fun showToast(context: Context, stringId: Int) {
        val toast = Toast.makeText(context, stringId, Toast.LENGTH_SHORT)
        toast.setGravity(
            Gravity.BOTTOM,
            TOAST_OFFSET_X,
            TOAST_OFFSET_Y
        )
        toast.show()
    }

}