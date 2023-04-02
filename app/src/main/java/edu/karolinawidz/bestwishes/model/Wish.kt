package edu.karolinawidz.bestwishes.model

import edu.karolinawidz.bestwishes.enum.CardType

data class Wish(val id: String, val stringResourceId: Int, val type: CardType, var isSet: Boolean)
