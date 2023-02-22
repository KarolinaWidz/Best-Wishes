package edu.karolinawidz.bestwishes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import edu.karolinawidz.bestwishes.enum.CardType

data class Picture(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    val type: CardType
)