package edu.karolinawidz.bestwishes.data

import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.model.Picture

class PictureDatasource {
    fun loadPictures(): List<Picture> {
        return listOf(
            Picture(R.string.birthday_picture1, R.drawable.birthday1),
            Picture(R.string.birthday_picture2, R.drawable.birthday2),
            Picture(R.string.birthday_picture3, R.drawable.birthday3),
            Picture(R.string.birthday_picture4, R.drawable.birthday4),
            Picture(R.string.birthday_picture5, R.drawable.birthday5),
            Picture(R.string.birthday_picture6, R.drawable.birthday6),
            Picture(R.string.birthday_picture7, R.drawable.birthday7),
            Picture(R.string.birthday_picture8, R.drawable.birthday8),
            Picture(R.string.birthday_picture9, R.drawable.birthday9),
            Picture(R.string.birthday_picture10, R.drawable.birthday10),
            Picture(R.string.birthday_picture11, R.drawable.birthday11),
            Picture(R.string.birthday_picture12, R.drawable.birthday12),
            Picture(R.string.birthday_picture13, R.drawable.birthday13),
        )
    }
}