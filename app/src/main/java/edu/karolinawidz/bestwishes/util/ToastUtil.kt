package edu.karolinawidz.bestwishes.util

import android.content.Context
import android.view.Gravity
import android.widget.Toast

object ToastUtil {
    private const val TOAST_OFFSET_X = 0
    private const val TOAST_OFFSET_Y = 220

    fun showNoPictureSelectedToast(context: Context, stringId: Int) {
        val text = context.getString(stringId)
        val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
        toast.setGravity(
            Gravity.BOTTOM,
            TOAST_OFFSET_X,
            TOAST_OFFSET_Y
        )
        toast.show()
    }

}