package edu.karolinawidz.bestwishes.data

import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.enum.CardType
import edu.karolinawidz.bestwishes.model.Picture

class PictureDatasource {
    fun loadPictures(): List<Picture> {
        return listOf(
            Picture(R.string.birthday_picture1, R.drawable.birthday1, CardType.BIRTHDAY),
            Picture(R.string.birthday_picture2, R.drawable.birthday2, CardType.BIRTHDAY),
            Picture(R.string.birthday_picture3, R.drawable.birthday3, CardType.BIRTHDAY),
            Picture(R.string.birthday_picture4, R.drawable.birthday4, CardType.BIRTHDAY),
            Picture(R.string.birthday_picture5, R.drawable.birthday5, CardType.BIRTHDAY),
            Picture(R.string.birthday_picture6, R.drawable.birthday6, CardType.BIRTHDAY),
            Picture(R.string.birthday_picture7, R.drawable.birthday7, CardType.BIRTHDAY),
            Picture(R.string.birthday_picture8, R.drawable.birthday8, CardType.BIRTHDAY),
            Picture(R.string.birthday_picture9, R.drawable.birthday9, CardType.BIRTHDAY),
            Picture(R.string.birthday_picture10, R.drawable.birthday10, CardType.BIRTHDAY),
            Picture(R.string.birthday_picture11, R.drawable.birthday11, CardType.BIRTHDAY),
            Picture(R.string.birthday_picture12, R.drawable.birthday12, CardType.BIRTHDAY),
            Picture(R.string.birthday_picture13, R.drawable.birthday13, CardType.BIRTHDAY),
            Picture(R.string.anniversary_picture1, R.drawable.anniversary1, CardType.ANNIVERSARY),
            Picture(R.string.anniversary_picture2, R.drawable.anniversary2, CardType.ANNIVERSARY),
            Picture(R.string.anniversary_picture3, R.drawable.anniversary3, CardType.ANNIVERSARY),
            Picture(R.string.anniversary_picture4, R.drawable.anniversary4, CardType.ANNIVERSARY),
            Picture(R.string.anniversary_picture5, R.drawable.anniversary5, CardType.ANNIVERSARY)
        )
    }
}