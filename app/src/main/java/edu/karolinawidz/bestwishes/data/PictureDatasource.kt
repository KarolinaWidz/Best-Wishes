package edu.karolinawidz.bestwishes.data

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.enum.CardType
import edu.karolinawidz.bestwishes.model.Picture

const val RESOURCE_PATH = "android.resource://edu.karolinawidz.bestwishes/drawable/"

object PictureDatasource {
    fun loadPictures(): MutableLiveData<List<Picture>> {
        val pictureList = mutableListOf(
            Picture(
                R.string.birthday_picture1,
                Uri.parse("$RESOURCE_PATH/birthday1"),
                CardType.BIRTHDAY
            ),
            Picture(
                R.string.birthday_picture2,
                Uri.parse("$RESOURCE_PATH/birthday2"),
                CardType.BIRTHDAY
            ),
            Picture(
                R.string.birthday_picture3,
                Uri.parse("$RESOURCE_PATH/birthday3"),
                CardType.BIRTHDAY
            ),
            Picture(
                R.string.birthday_picture4,
                Uri.parse("$RESOURCE_PATH/birthday4"),
                CardType.BIRTHDAY
            ),
            Picture(
                R.string.birthday_picture5,
                Uri.parse("$RESOURCE_PATH/birthday5"),
                CardType.BIRTHDAY
            ),
            Picture(
                R.string.birthday_picture6,
                Uri.parse("$RESOURCE_PATH/birthday6"),
                CardType.BIRTHDAY
            ),
            Picture(
                R.string.birthday_picture7,
                Uri.parse("$RESOURCE_PATH/birthday7"),
                CardType.BIRTHDAY
            ),
            Picture(
                R.string.birthday_picture8,
                Uri.parse("$RESOURCE_PATH/birthday8"),
                CardType.BIRTHDAY
            ),
            Picture(
                R.string.birthday_picture9,
                Uri.parse("$RESOURCE_PATH/birthday9"),
                CardType.BIRTHDAY
            ),
            Picture(
                R.string.birthday_picture10,
                Uri.parse("$RESOURCE_PATH/birthday10"),
                CardType.BIRTHDAY
            ),
            Picture(
                R.string.birthday_picture11,
                Uri.parse("$RESOURCE_PATH/birthday11"),
                CardType.BIRTHDAY
            ),
            Picture(
                R.string.birthday_picture12,
                Uri.parse("$RESOURCE_PATH/birthday12"),
                CardType.BIRTHDAY
            ),
            Picture(
                R.string.birthday_picture13,
                Uri.parse("$RESOURCE_PATH/birthday13"),
                CardType.BIRTHDAY
            ),
            Picture(
                R.string.anniversary_picture1,
                Uri.parse("$RESOURCE_PATH/anniversary1"),
                CardType.ANNIVERSARY
            ),
            Picture(
                R.string.anniversary_picture2,
                Uri.parse("$RESOURCE_PATH/anniversary2"),
                CardType.ANNIVERSARY
            ),
            Picture(
                R.string.anniversary_picture3,
                Uri.parse("$RESOURCE_PATH/anniversary3"),
                CardType.ANNIVERSARY
            ),
            Picture(
                R.string.anniversary_picture4,
                Uri.parse("$RESOURCE_PATH/anniversary4"),
                CardType.ANNIVERSARY
            ),
            Picture(
                R.string.anniversary_picture5,
                Uri.parse("$RESOURCE_PATH/anniversary5"),
                CardType.ANNIVERSARY
            )
        )
        return MutableLiveData(pictureList)
    }

}