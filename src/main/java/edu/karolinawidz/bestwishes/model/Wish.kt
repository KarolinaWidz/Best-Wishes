package edu.karolinawidz.bestwishes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Wish(@StringRes val stringResourceId: Int, @DrawableRes val imageResourceId: Int)