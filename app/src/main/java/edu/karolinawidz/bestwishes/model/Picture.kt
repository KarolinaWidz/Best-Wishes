package edu.karolinawidz.bestwishes.model

import android.net.Uri
import androidx.annotation.StringRes
import edu.karolinawidz.bestwishes.enum.CardType

data class Picture(
    val id: String,
    @StringRes
    val stringResourceId: Int,
    val imageUri: Uri,
    val type: CardType,
    var isSet: Boolean
)