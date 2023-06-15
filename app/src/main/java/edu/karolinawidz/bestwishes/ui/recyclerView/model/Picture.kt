package edu.karolinawidz.bestwishes.ui.recyclerView.model

import android.net.Uri
import edu.karolinawidz.bestwishes.enums.CardType

data class Picture(
    val id: String,
    val description: String,
    val imageUri: Uri,
    val type: CardType,
    var isSet: Boolean
)