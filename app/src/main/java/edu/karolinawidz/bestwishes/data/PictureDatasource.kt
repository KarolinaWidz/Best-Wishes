package edu.karolinawidz.bestwishes.data

import android.net.Uri
import edu.karolinawidz.bestwishes.enum.CardType
import edu.karolinawidz.bestwishes.model.listItems.Picture
import java.util.*

const val RESOURCE_PATH = "android.resource://edu.karolinawidz.bestwishes/drawable/"

object PictureDatasource {
    fun loadPictures(): List<Picture> {
        return mutableListOf(
            Picture(
                UUID.randomUUID().toString(),
                "Cake 1",
                Uri.parse("$RESOURCE_PATH/birthday1"),
                CardType.BIRTHDAY,
                false
            ),
            Picture(
                UUID.randomUUID().toString(),
                "Frog with cake",
                Uri.parse("$RESOURCE_PATH/birthday2"),
                CardType.BIRTHDAY,
                false
            ),
            Picture(
                UUID.randomUUID().toString(),
                "Birthday cake",
                Uri.parse("$RESOURCE_PATH/birthday3"),
                CardType.BIRTHDAY,
                false
            ),
            Picture(
                UUID.randomUUID().toString(),
                "Birthday cupcakes",
                Uri.parse("$RESOURCE_PATH/birthday4"),
                CardType.BIRTHDAY,
                false
            ),
            Picture(
                UUID.randomUUID().toString(),
                "Champagne",
                Uri.parse("$RESOURCE_PATH/birthday5"),
                CardType.BIRTHDAY,
                false
            ),
            Picture(
                UUID.randomUUID().toString(),
                "Gift boxes",
                Uri.parse("$RESOURCE_PATH/birthday6"),
                CardType.BIRTHDAY,
                false
            ),
            Picture(
                UUID.randomUUID().toString(),
                "Birthday cake 2",
                Uri.parse("$RESOURCE_PATH/birthday7"),
                CardType.BIRTHDAY,
                false
            ),
            Picture(
                UUID.randomUUID().toString(),
                "Birthday cake 3",
                Uri.parse("$RESOURCE_PATH/birthday8"),
                CardType.BIRTHDAY,
                false
            ),
            Picture(
                UUID.randomUUID().toString(),
                "Cake 2",
                Uri.parse("$RESOURCE_PATH/birthday9"),
                CardType.BIRTHDAY,
                false
            ),
            Picture(
                UUID.randomUUID().toString(),
                "Gift boxes 2",
                Uri.parse("$RESOURCE_PATH/birthday10"),
                CardType.BIRTHDAY,
                false
            ),
            Picture(
                UUID.randomUUID().toString(),
                "Stones",
                Uri.parse("$RESOURCE_PATH/birthday11"),
                CardType.BIRTHDAY,
                false
            ),
            Picture(
                UUID.randomUUID().toString(),
                "Birthday cake 2",
                Uri.parse("$RESOURCE_PATH/birthday12"),
                CardType.BIRTHDAY,
                false
            ),
            Picture(
                UUID.randomUUID().toString(),
                "Balloons",
                Uri.parse("$RESOURCE_PATH/birthday13"),
                CardType.BIRTHDAY,
                false
            ),
            Picture(
                UUID.randomUUID().toString(),
                "Cups",
                Uri.parse("$RESOURCE_PATH/anniversary1"),
                CardType.ANNIVERSARY,
                false
            ),
            Picture(
                UUID.randomUUID().toString(),
                "Wedding couple",
                Uri.parse("$RESOURCE_PATH/anniversary2"),
                CardType.ANNIVERSARY,
                false
            ),
            Picture(
                UUID.randomUUID().toString(),
                "Elderly couple",
                Uri.parse("$RESOURCE_PATH/anniversary3"),
                CardType.ANNIVERSARY,
                false
            ),
            Picture(
                UUID.randomUUID().toString(),
                "Teddy bears",
                Uri.parse("$RESOURCE_PATH/anniversary4"),
                CardType.ANNIVERSARY,
                false
            ),
            Picture(
                UUID.randomUUID().toString(),
                "Sunshine",
                Uri.parse("$RESOURCE_PATH/anniversary5"),
                CardType.ANNIVERSARY,
                false
            )
        )
    }
}