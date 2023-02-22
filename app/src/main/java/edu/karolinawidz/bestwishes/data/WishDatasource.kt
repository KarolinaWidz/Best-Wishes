package edu.karolinawidz.bestwishes.data

import edu.karolinawidz.bestwishes.R
import edu.karolinawidz.bestwishes.enum.CardType
import edu.karolinawidz.bestwishes.model.Wish

class WishDatasource {
    fun loadWishes(): List<Wish> {
        return listOf(
            Wish(R.string.birthday1, CardType.BIRTHDAY),
            Wish(R.string.birthday2, CardType.BIRTHDAY),
            Wish(R.string.birthday3, CardType.BIRTHDAY),
            Wish(R.string.birthday4, CardType.BIRTHDAY),
            Wish(R.string.birthday5, CardType.BIRTHDAY),
            Wish(R.string.birthday6, CardType.BIRTHDAY),
            Wish(R.string.birthday7, CardType.BIRTHDAY),
            Wish(R.string.birthday8, CardType.BIRTHDAY),
            Wish(R.string.birthday9, CardType.BIRTHDAY),
            Wish(R.string.birthday10, CardType.BIRTHDAY),
            Wish(R.string.birthday11, CardType.BIRTHDAY),
            Wish(R.string.birthday12, CardType.BIRTHDAY),
            Wish(R.string.birthday13, CardType.BIRTHDAY),
            Wish(R.string.anniversary1, CardType.ANNIVERSARY),
            Wish(R.string.anniversary2, CardType.ANNIVERSARY),
            Wish(R.string.anniversary3, CardType.ANNIVERSARY),
            Wish(R.string.anniversary4, CardType.ANNIVERSARY),
            Wish(R.string.anniversary5, CardType.ANNIVERSARY),
        )
    }
}