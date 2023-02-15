package edu.karolinawidz.bestwishes.utils

import android.content.Context
import android.graphics.*
import androidx.core.content.ContextCompat
import edu.karolinawidz.bestwishes.R
import kotlin.math.roundToInt

class PictureGenerator {

    companion object {
        private const val FONT_SIZE = 40F
        private const val MARGIN = 10
        private const val HORIZONTAL_MARGIN = 40

        fun createCardBase(context: Context, drawableId: Int): Bitmap? {
            val bitmap = BitmapFactory.decodeResource(context.resources, drawableId)
            val resultBitmap = Bitmap.createBitmap(700, 500, bitmap.config)
            val canvas = Canvas(resultBitmap)
            canvas.drawColor(Color.WHITE)


            val textPaint = Paint().apply {
                textAlign = Paint.Align.CENTER
                textSize = FONT_SIZE
                typeface = Typeface.MONOSPACE
                color = ContextCompat.getColor(context, R.color.final_font_color)
            }
            val headingString = context.resources.getString(R.string.happy_birthday_text)
            val testRect = Rect()
            textPaint.getTextBounds(headingString, 0, headingString.lastIndex, testRect)
            val fm = textPaint.fontMetrics
            val height = (fm.descent - fm.ascent).roundToInt()

            canvas.drawText(
                headingString,
                canvas.width / 2F,
                height.toFloat(),
                textPaint
            )
            canvas.drawBitmap(
                bitmap,
                null,
                Rect(
                    HORIZONTAL_MARGIN,
                    height + MARGIN, canvas.width - HORIZONTAL_MARGIN, canvas.height
                ),
                Paint()
            )
            return resultBitmap
        }
    }
}