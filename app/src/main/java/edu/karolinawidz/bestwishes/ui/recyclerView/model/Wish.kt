package edu.karolinawidz.bestwishes.ui.recyclerView.model

import edu.karolinawidz.bestwishes.enums.CardType

data class Wish(val id: String, val stringResourceId: Int, val type: CardType, var isSet: Boolean)
