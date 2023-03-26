package edu.karolinawidz.bestwishes.util

import android.graphics.*
import android.text.StaticLayout
import android.text.TextPaint
import kotlin.math.roundToInt

object PictureGenerator {
    private const val HEADING_FONT_SIZE = 60F
    private const val FONT_SIZE = 50F
    private const val MARGIN = 20
    private const val HORIZONTAL_MARGIN = 40
    private const val HEADING_HEIGHT = 60F
    private const val PICTURE_HEIGHT = 1800
    private const val PICTURE_WIDTH = 1000
    private const val PICTURE_WIDTH_CENTER = PICTURE_WIDTH / 2F

    fun createCard(
        picture: Bitmap,
        bodyText: CharSequence,
        heading: String,
        font: Typeface?,
        fontColor: Int
    ): Bitmap? {

        val temporaryBitmap = Bitmap.createBitmap(PICTURE_WIDTH, PICTURE_HEIGHT, picture.config)
        val canvas = Canvas(temporaryBitmap)
        canvas.drawColor(Color.WHITE)

        val textPaint = TextPaint().apply {
            textAlign = Paint.Align.CENTER
            textSize = HEADING_FONT_SIZE
            typeface = font
            color = fontColor
        }

        canvas.drawText(
            heading,
            PICTURE_WIDTH_CENTER,
            HEADING_HEIGHT,
            textPaint
        )

        drawPictureOnCanvas(canvas, picture, textPaint)
        val finalSize = drawTextBodyAndCalculateFinalSize(canvas, bodyText, textPaint)

        return Bitmap.createBitmap(
            temporaryBitmap, 0, 0,
            PICTURE_WIDTH,
            finalSize,
            null, false
        )
    }

    private fun drawPictureOnCanvas(
        canvas: Canvas,
        picture: Bitmap,
        textPaint: TextPaint
    ) {
        val pictureHeight =
            (HEADING_HEIGHT + textPaint.fontMetrics.bottom + MARGIN).roundToInt()
        canvas.drawBitmap(
            picture,
            null,
            Rect(
                HORIZONTAL_MARGIN,
                pictureHeight,
                canvas.width - HORIZONTAL_MARGIN,
                canvas.width * 3 / 4
            ),
            Paint()
        )
    }

    private fun drawTextBodyAndCalculateFinalSize(
        canvas: Canvas,
        bodyText: CharSequence,
        textPaint: TextPaint
    ): Int {

        textPaint.textSize = FONT_SIZE
        val textLayout = StaticLayout.Builder.obtain(
            bodyText,
            0,
            bodyText.lastIndex,
            textPaint,
            canvas.width - 2 * HORIZONTAL_MARGIN
        ).build()

        canvas.save()
        val y = canvas.width * 3 / 4F + MARGIN
        canvas.translate(canvas.width / 2F, y)
        textLayout.draw(canvas)
        canvas.restore()
        return (y + MARGIN + textLayout.height).roundToInt()
    }
}