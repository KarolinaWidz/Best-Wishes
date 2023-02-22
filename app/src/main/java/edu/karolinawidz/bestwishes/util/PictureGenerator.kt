package edu.karolinawidz.bestwishes.util

import android.content.Context
import android.graphics.*
import android.text.StaticLayout
import android.text.TextPaint
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import edu.karolinawidz.bestwishes.R
import kotlin.math.roundToInt

class PictureGenerator {

    companion object {
        private const val HEADING_FONT_SIZE = 60F
        private const val FONT_SIZE = 50F
        private const val MARGIN = 20
        private const val HORIZONTAL_MARGIN = 40
        private const val HEADING_HEIGHT = 60F
        private const val PICTURE_HEIGHT = 1800
        private const val PICTURE_WIDTH = 1000

        fun createCardBase(
            context: Context,
            drawableId: Int,
            stringId: Int,
            heading: String
        ): Bitmap? {
            val bitmap = BitmapFactory.decodeResource(context.resources, drawableId)
            val temporaryBitmap = Bitmap.createBitmap(PICTURE_WIDTH, PICTURE_HEIGHT, bitmap.config)
            val canvas = Canvas(temporaryBitmap)
            canvas.drawColor(Color.WHITE)

            val textPaint = TextPaint().apply {
                textAlign = Paint.Align.CENTER
                textSize = HEADING_FONT_SIZE
                typeface = ResourcesCompat.getFont(context, R.font.card_firstschool)
                color = ContextCompat.getColor(context, R.color.final_font_color)
            }

            val pictureHeight =
                (HEADING_HEIGHT + textPaint.fontMetrics.bottom + MARGIN).roundToInt()

            canvas.drawText(
                heading,
                canvas.width / 2F,
                HEADING_HEIGHT,
                textPaint
            )

            canvas.drawBitmap(
                bitmap,
                null,
                Rect(
                    HORIZONTAL_MARGIN,
                    pictureHeight,
                    canvas.width - HORIZONTAL_MARGIN,
                    canvas.width * 3 / 4
                ),
                Paint()
            )

            textPaint.textSize = FONT_SIZE
            val text = context.resources.getText(stringId)
            val textLayout = StaticLayout.Builder.obtain(
                text,
                0,
                text.lastIndex,
                textPaint,
                canvas.width - 2 * HORIZONTAL_MARGIN
            ).build()

            canvas.save()
            val y = canvas.width * 3 / 4F + MARGIN
            canvas.translate(canvas.width / 2F, y)
            textLayout.draw(canvas)
            canvas.restore()
            val finalSize = (y + MARGIN + textLayout.height).roundToInt()

            return Bitmap.createBitmap(
                temporaryBitmap, 0, 0,
                PICTURE_WIDTH,
                finalSize,
                null, false
            )
        }
    }
}