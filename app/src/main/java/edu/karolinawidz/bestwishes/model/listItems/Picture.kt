package edu.karolinawidz.bestwishes.model.listItems

import android.net.Uri
import edu.karolinawidz.bestwishes.enum.CardType

data class Picture(
    val id: String,
    val description: String,
    val imageUri: Uri,
    val type: CardType,
    var isSet: Boolean
)