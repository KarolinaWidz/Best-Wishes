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
    private const val PORTRAIT_HORIZONTAL_MARGIN = 262
    private const val HEADING_HEIGHT = 60F
    private const val CARD_HEIGHT = 1800
    private const val CARD_WIDTH = 1200
    private const val CARD_WIDTH_CENTER = CARD_WIDTH / 2F

    fun createCard(
        picture: Bitmap,
        bodyText: CharSequence,
        heading: String,
        font: Typeface?,
        fontColor: Int
    ): Bitmap? {

        val temporaryBitmap = Bitmap.createBitmap(CARD_WIDTH, CARD_HEIGHT, picture.config)
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
            CARD_WIDTH_CENTER,
            HEADING_HEIGHT,
            textPaint
        )

        drawPictureOnCanvas(canvas, picture, textPaint)
        val finalSize = drawTextBodyAndCalculateFinalSize(canvas, bodyText, textPaint)

        return Bitmap.createBitmap(
            temporaryBitmap, 0, 0,
            CARD_WIDTH,
            finalSize,
            null, false
        )
    }

    private fun drawPictureOnCanvas(
        canvas: Canvas,
        picture: Bitmap,
        textPaint: TextPaint
    ) {
        val pictureStart =
            (HEADING_HEIGHT + textPaint.fontMetrics.bottom + MARGIN).roundToInt()
        val pictureEnd = canvas.width * 3 / 4
        val margin =
            if (picture.width > picture.height) HORIZONTAL_MARGIN else PORTRAIT_HORIZONTAL_MARGIN

        canvas.drawBitmap(
            picture,
            null,
            Rect(
                margin,
                pictureStart,
                canvas.width - margin,
                pictureEnd
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